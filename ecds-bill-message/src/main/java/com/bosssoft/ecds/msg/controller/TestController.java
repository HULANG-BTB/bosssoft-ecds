package com.bosssoft.ecds.msg.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxiaohui
 * @create 2020/8/17 21:09
 */
@RequestMapping
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public void test(String value){
        log.info(value);
    }
}
