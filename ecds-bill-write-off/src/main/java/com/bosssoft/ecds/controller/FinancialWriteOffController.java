package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.vo.SearchFromVo;
import com.bosssoft.ecds.service.FinancialWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 财务段的核销服务
 */
@RestController
@RequestMapping("/financial")
@CrossOrigin
public class FinancialWriteOffController {

    @Autowired
    private FinancialWriteOffService financialWriteOffService;

    /**
     * 获取单位端传来的核销信息
     * 接收一段时间的下级单位传来的核销信息
     *
     * @param object
     * @return java.util.List
     */
    @ResponseBody
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public List<Object> receive(Object object) {
        return financialWriteOffService.receive(object);
    }

    /**
     * 退回单位端传来的核销信息
     * 需要退回的核销信息根据单位ID回到单位端进行修改
     *
     * @param list
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendBack", method = RequestMethod.POST)
    public boolean sendBack(List<Object> list) {
        return financialWriteOffService.sendBack(list);
    }

    /**
     * 获取核销信息详情
     *
     * @param object
     * @return java.lang.Object
     */
    @ResponseBody
    @RequestMapping(value = "/getDetails", method = RequestMethod.POST)
    public Object getDetails(Object object) {
        return financialWriteOffService.getDetails(object);
    }

    /**
     * 获取单位电子档案
     *
     * @param object
     * @return java.lang.Object
     */
    @ResponseBody
    @RequestMapping(value = "/getUnitDetails", method = RequestMethod.POST)
    public Object getUnitDetails(Object object) {
        return financialWriteOffService.getUnitDetails(object);
    }

    /**
     * 存入核销结果
     *
     * @param object
     * @return java.lang.Object
     */
    @ResponseBody
    @RequestMapping(value = "/setResult", method = RequestMethod.POST)
    public boolean setResult(Object object) {
        return financialWriteOffService.setResult(object);
    }

    /**
     * 实现搜索功能
     * 后端搜索完对多条数据进行分页
     *
     * @param searchFromVo
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestBody SearchFromVo searchFromVo){
        System.out.println(searchFromVo.getNumber());
        return "success";
    }
}
