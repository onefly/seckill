package com.seckill.dao;

import com.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 秒杀商品相关dao接口
 * Created by caozhifei on 2016/5/15.
 */
public interface SeckillDao {
    /**
     * 减库存操作
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceStock(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);

    /**
     * 根据ID查询秒杀商品
     * @param id
     * @return
     */
    Seckill queryById(long id);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit")int limit);

    /**
     * 使用存储过程执行秒杀操作
     * @param paramMap
     */
    void killByProcedure(Map<String,Object> paramMap);
}
