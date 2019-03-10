package com.seckill.service.impl;

import com.seckill.dao.OrderBaseDao;
import com.seckill.entity.OrderBase;
import com.seckill.service.OrderBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/3/10.
 */
@Service
public class OrderBaseServiceImpl implements OrderBaseService {
    @Autowired
    private OrderBaseDao orderBaseDao;
    @Override
    public List<OrderBase> getOrderBaseList(String workerIp, Byte status) {
        return orderBaseDao.getOrderBaseList(workerIp, status);
    }

    @Override
    public int updateWorkerIpByStatus(String workerIp, Byte status) {
        return orderBaseDao.updateWorkerIpByStatus(workerIp, status);
}

    @Override
    public int updateStatusById(Long id, Byte status) {
        return orderBaseDao.updateStatusById(id, status);
    }
}
