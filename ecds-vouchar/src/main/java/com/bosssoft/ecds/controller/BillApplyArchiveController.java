package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.service.BillApplyArchiveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  向前端展示票据申领的数据
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Api("归档领用")
@RestController
@RequestMapping("/apply")
public class BillApplyArchiveController {

    @Autowired
    BillApplyArchiveService service;
    /**
     * 获取单位端的票据领用情况
     * @return  单位端的票据领用情况
     */
    @GetMapping("/unit/info")
    public String info(){

        return "";
    }

    /**
     * 财政端 获取所有单位的票据领用情况
     * @return
     */
    @GetMapping("/fina/info")
    public String infos(){

        return "";
    }
}

