package com.seckill.service;

import com.seckill.entity.OrderInquiry;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/9/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml"})
public class OrderInquiryServiceTest extends TestCase {
    @Autowired
    private OrderInquiryService orderInquiryService;

    @Test
    public void testAdd() throws Exception {
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setTableNum(5);
        for (long i = 1; i < 1000; i++) {
            orderInquiry.setOid(i);
            orderInquiry.setSplitId(i);
            orderInquiry.setAttrId(1000 + i);
            orderInquiry.setAttrValue("value" + i);
            orderInquiryService.add(orderInquiry);
        }
    }
}