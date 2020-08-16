package com.bosssoft.ecds.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 库存管理服务
 * 这里用Feign进行Http的请求结果的解析和封装
 */
@FeignClient(value = "", path = "")
public interface StockService {

    /**
     * 获取单位所有的票据信息
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    List<Object> list();
}
