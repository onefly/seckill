package com.seckill.controller;

import com.seckill.entity.Seckill;
import com.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by caozhifei on 2016/5/16.
 */
@Controller
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    @RequestMapping("list")
    @ResponseBody
    public Object list() {
        List<Seckill> list = seckillService.getSeckillList();
        return list;
    }
    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello springmvc";
    }
}
