package com.seckill.service;

import com.seckill.entity.OrderBase;

import java.util.List;

/**
 * Created by Administrator on 2019/3/10.
 */
public interface OrderBaseService {
    List<OrderBase> getOrderBaseList(String workerIp,Byte status);

    int updateWorkerIpByStatus(String workerIp,Byte status);

    int updateStatusById(Long id ,Byte status);
}
