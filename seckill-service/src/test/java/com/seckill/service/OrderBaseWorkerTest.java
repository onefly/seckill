package com.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/3/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml"})
public class OrderBaseWorkerTest {
    @Test
    public void test(){
        try {
            TimeUnit.MINUTES.sleep(90L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
