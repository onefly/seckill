package com.seckill.service.impl;

import com.seckill.dao.OrderInquiryDao;
import com.seckill.entity.OrderInquiry;
import com.seckill.service.OrderInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/10.
 */
@Service
public class OrderInquiryServiceImpl implements OrderInquiryService {
    @Autowired
    private OrderInquiryDao orderInquiryDao;
    public void add(OrderInquiry orderInquiry) {
        orderInquiryDao.add(orderInquiry);
    }
}
