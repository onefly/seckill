package com.seckill.dao;

import com.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Test
    public void testInsertSuccessKilled() throws Exception {
        long id = 1;
        long phone = 15099999999L;
        byte state = 1;
        int insertCount = successKilledDao.insertSuccessKilled(id,phone,state);
        System.out.println("insertCount="+insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        long id = 1;
        long phone = 15099999999L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}