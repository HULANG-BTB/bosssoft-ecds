package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.service.UnitManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/billInvoicing")
public class UneCBillController {

    @Autowired
    private UnitManagerService unitManagerService;

    /**
     * 新增开票
     * 所需字段：
     *单位（通过UnitManagerService调用远程接口查询）：区划id，单位识别码，单位编码，开票点id，开票点编码，开票点名称
     *缴款人信息 http request body中直接获取
     *项目（接收项目列表）：项目编码，项目名称，收费标准，计量单位
     *编制人：当前登录用户(redis中获取)
     * 校验码：commonUtil工具类生成
     * @return
     */
    @RequestMapping(path = "/addUnebill", method = RequestMethod.POST)
    public String addUneCbill() {
        //1 查询单位信息
        String unitInfo = unitManagerService.getDetailByUnitName("unitName");

        return "";
    }

    /**
     * 开票选择开票项目列表
     * 项目（通过UnitManagerService调用远程接口查询单位包含项目list）：项目编码，项目名称，收费标准，计量单位
     * @return
     */
    @RequestMapping(path = "getItemList", method = RequestMethod.GET)
    public String getItemLisst() {
        String itemList = unitManagerService.getItemList("unitName");
        //转换成json字符串传到前端
        return "";
    }
}
