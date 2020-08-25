package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.entity.dto.AgenInfoDTO;
import com.bosssoft.ecds.entity.dto.ArrearDTO;
import com.bosssoft.ecds.entity.dto.ItemInfoDTO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.fallback.FallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ecds-base", fallback = FallBack.class)
public interface UnitManagerService {

    /**
     * 单位是否欠缴
     * @param agenName
     * @return
     */
    @PostMapping(path = "/agen/isArrEar")
    ArrearDTO isArrear(@RequestParam("agenName")String agenName);

    /**
     * 查询单位详细信息
     * 根据单位名称查询所需数据
     * 单位：区划id，单位编码，开票点id，开票点编码，开票点名称
     * @param ageName
     * @return
     */
    @PostMapping(path = "/agen/getDetailByUnitName")
    AgenInfoDTO getDetailByUnitName(@RequestParam("agenName")String ageName);

    /**
     * 查询单位下所对应的的项目列表
     * 项目编码，项目名称，收费标准，计量单位
     * @param unitName
     * @return
     */
    @PostMapping(path = "/agenItem/getItemInfo")
    QueryResponseResult getItemInfo(@RequestParam("agenName")String unitName);
}
