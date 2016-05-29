package com.seckill.service.impl;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKilledDao;
import com.seckill.dao.cache.RedisDao;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.enums.SeckillStateEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillColseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 秒杀服务接口实现类
 * Created by caozhifei on 2016/5/15.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 注入秒杀商品dao依赖
     */
    @Autowired
    private SeckillDao seckillDao;
    /**
     * 注入秒杀成功明细dao
     */
    @Autowired
    private SuccessKilledDao successKilledDao;
    /**
     * 缓存dao
     */
    @Autowired
    private RedisDao redisDao;
    /**
     * 加密的盐
     */
    private String slat = "skjeljfdslkfudsjg789";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 15);
    }

    @Override
    public Seckill getById(long id) {
        return seckillDao.queryById(id);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        //缓存优化
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill == null){
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            }else{
                //放入缓存
                String ok = redisDao.putSeckill(seckill);
                logger.error("cache put result:"+ok);
            }
        }

        long startTime = seckill.getStartTime().getTime();
        long endTime = seckill.getEndTime().getTime();
        //系统当前时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < startTime || nowTime > endTime) {
            return new Exposer(false, seckillId, nowTime, startTime, endTime);
        }
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillColseException {
        if (md5 == null || !md5.endsWith(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        // 执行秒杀逻辑： 减库存 + 记录秒杀成功记录
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceStock(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录;秒杀结束
                throw new SeckillColseException("seckill is closed");
            } else {
                //记录秒杀成功记录
                byte state = 1;
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone, state);
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀记录成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillColseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常转化为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    /**
     * 获取md5加密值
     *
     * @param seckillId
     * @return
     */
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
