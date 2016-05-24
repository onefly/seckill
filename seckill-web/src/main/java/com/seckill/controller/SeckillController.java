package com.seckill.controller;

import com.seckill.entity.Seckill;
import com.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by caozhifei on 2016/5/16.
 */
@RestController
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    @RequestMapping("list")
    public Object list() {
        List<Seckill> list = seckillService.getSeckillList();
        return list;
    }
    @RequestMapping("hello")
    public String hello() {
        return "hello springmvc";
    }
}
