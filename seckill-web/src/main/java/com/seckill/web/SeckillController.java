package com.seckill.web;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.JsonResult;
import com.seckill.entity.Seckill;
import com.seckill.enums.SeckillStateEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillColseException;
import com.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by caozhifei on 2016/5/16.
 */
@Controller
@RequestMapping("seckill")
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @RequestMapping("list")
    public String list(ModelMap model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.put("list", list);
        return "list";
    }

    @RequestMapping("{id}/detail")
    public String detail(@PathVariable("id") Long id, ModelMap model) {
        if(id == null){
            return "redirect:/seckill/list";
        }
        Seckill detail = seckillService.getById(id);
        if(detail == null){
            return "forward:/seckill/list";
        }
        model.put("detail", detail);
        return "detail";
    }

    @RequestMapping("{id}/exposer")
    @ResponseBody
    public JsonResult<Exposer> exposer(@PathVariable("id") Long id){
        JsonResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(id);
            result = new JsonResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error("exposer error",e);
            result = new JsonResult<Exposer>(false,e.getMessage());
        }
        return result;
    }
    @RequestMapping("{id}/{md5}/execution")
    @ResponseBody
    public JsonResult<SeckillExecution> execute(@PathVariable("id") Long id,@PathVariable(value = "md5")String md5,@CookieValue(value = "phone", required = false)Long phone){
        JsonResult<SeckillExecution> result;
        if(phone == null){
            return new JsonResult<SeckillExecution>(false,"用户没有登录");
        }
        SeckillExecution execution;
        try {
             execution = seckillService.executeSeckill(id, phone, md5);
            return new JsonResult<SeckillExecution>(true,execution);
        }catch (SeckillColseException e){
            execution = new SeckillExecution(id, SeckillStateEnum.END);

        }catch (RepeatKillException e){
            execution = new SeckillExecution(id, SeckillStateEnum.REPEAT_KILL);
        }catch (Exception e){
            execution = new SeckillExecution(id, SeckillStateEnum.INNER_ERROR);
            logger.error("kill inner error",e);
        }
        return new JsonResult<SeckillExecution>(true,execution);
    }

    @RequestMapping("time/now")
    @ResponseBody
    public JsonResult<Long> time() {
        return new JsonResult<Long>(true,System.currentTimeMillis());
    }
}
