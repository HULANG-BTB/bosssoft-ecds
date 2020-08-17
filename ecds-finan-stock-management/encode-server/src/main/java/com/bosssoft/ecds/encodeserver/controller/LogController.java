package com.bosssoft.ecds.encodeserver.controller;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 黄杰峰
 * @Date 2020/8/17 0017 10:19
 * @Description
 */
@Log4j2
@Slf4j
@RestController
public class LogController {

    @GetMapping("/log")
    public String log() {
        log.info("test");
        log.warn("test");
        return "test";
    }
}
