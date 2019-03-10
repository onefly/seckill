package com.seckill.dao;

import com.seckill.entity.OrderBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
public interface OrderBaseDao {
    List<OrderBase> getOrderBaseList(@Param("workerIp")String workerIp,@Param("status")Byte status);

    int updateWorkerIpByStatus(@Param("workerIp")String workerIp,@Param("status")Byte status);

    int updateStatusById(@Param("id")Long id ,@Param("status")Byte status);

}
