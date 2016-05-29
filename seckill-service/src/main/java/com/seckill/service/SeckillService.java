package com.seckill.service;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillColseException;
import com.seckill.exception.SeckillException;

import java.util.List;

/**
 * 秒杀服务接口
 * Created by caozhifei on 2016/5/15.
 */
public interface SeckillService {
    /**
     * 查询秒杀列表
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 根据ID查询秒杀商品信息
     * @param id
     * @return
     */
    Seckill getById(long id);

    /**
     * 秒杀开启后输出的秒杀地址
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
        throws SeckillException,RepeatKillException,SeckillColseException;

    /**
     * 执行秒杀操作, 利用mysql 存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckillProcedure(long seckillId,long userPhone,String md5);
}
