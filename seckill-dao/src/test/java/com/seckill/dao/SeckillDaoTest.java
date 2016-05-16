package com.seckill.dao;

import com.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 *配置spring和junit整合，junit启动时加载springICO容器
 * spring-test,junit
 */
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    /**
     * 注入dao实现类依赖
     */
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testReduceStock() throws Exception {
        long seckillId = 1;
        Date killTime = new Date();
        int updateCount = seckillDao.reduceStock(seckillId,killTime);
        System.out.println("updateCount="+updateCount);
    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> list = seckillDao.queryAll(0,15);
        for(Seckill seckill1: list) {
            System.out.println(seckill1);
        }
    }
}