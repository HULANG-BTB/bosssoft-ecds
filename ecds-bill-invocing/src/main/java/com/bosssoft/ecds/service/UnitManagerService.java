package com.bosssoft.ecds.service;

import com.bosssoft.ecds.fallback.FallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "", fallback = FallBack.class)
public interface UnitManagerService {

    /**
     * 调用接口
     * 根据单位名称或者单位识别码查询所需数据
     * 单位：区划id，单位识别码，单位编码，开票点id，开票点编码，开票点名称
     * @param unitName
     * @return
     */
    @RequestMapping(path = "/")
    String getDetailByUnitName(@RequestParam String unitName);

    /**
     * 查询单位下所对应的的项目列表
     * 项目编码，项目名称，收费标准，计量单位
     * @param unitName
     * @return
     */
    @RequestMapping(path = "/")
    String getItemList(@RequestParam String unitName);
}
