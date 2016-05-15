package com.seckill.dao;

import com.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by caozhifei on 2016/5/15.
 */
public interface SuccessKilledDao {
    /**
     * 插入秒杀明细
     * @param seckillId
     * @param userPhone
     * @param state
     * @return
     */
    int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone,@Param("state")byte state);

    /**
     * 查询秒杀明细
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
