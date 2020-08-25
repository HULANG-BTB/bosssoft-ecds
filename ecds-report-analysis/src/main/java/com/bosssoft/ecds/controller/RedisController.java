package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.utils.RedisConstants;
import com.bosssoft.ecds.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author syf
 * @Date 2020/8/12 16:52
 */

@Controller
@Slf4j
@RequestMapping(value="/redis")
public class RedisController{
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "getRedis")
    @ResponseBody
    public String getRedis(){
        redisUtil.set("20182018","这是一条测试数据", RedisConstants.datebase0);
        Long resExpire = redisUtil.expire("20182018", 60, RedisConstants.datebase0);
        log.info("resExpire="+resExpire);
        return redisUtil.get("20182018", RedisConstants.datebase0);
    }

}
