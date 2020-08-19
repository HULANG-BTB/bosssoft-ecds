package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.po.ApplyPo;
import com.bosssoft.ecds.entity.po.ItemPo;
import com.bosssoft.ecds.entity.vo.ApplyVo;
import com.bosssoft.ecds.service.ApplyService;
import com.bosssoft.ecds.service.ItemService;
import com.bosssoft.ecds.service.feign.Test;
import com.bosssoft.ecds.service.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: qiuheng
 * @create: 2020-08-12 15:22
 **/
@RestController
@RequestMapping(value = "apply")
@CrossOrigin
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "selectById")
    public ApplyPo selectById(Long id) {
        return applyService.selectById(id);
    }

    /**
     * @description: 前端获取票据销毁申请vo，拆分为itemVo和applyVo分别调用service
     * @param: [applyVo]
     * @return: java.lang.String
     * @date: 2020/8/13
     */
    @RequestMapping(value = "insertApplyInfo", method = RequestMethod.POST)
    @ResponseBody
    public String insertApplyInfo(@RequestBody ApplyVo applyVo) {
        applyService.insertApplyInfo(applyVo.getApplyDto());
        itemService.insertItemInfo(applyVo.getItemDtoList(), applyVo.getApplyDto().getfDestroyNo());
        return null;
    }

    /**
    * @description: 获取票据销毁申请列表
    * @param: []
    * @return: java.util.List<com.bosssoft.ecds.entity.po.ApplyPo>
    * @date: 2020/8/16
    */
    @RequestMapping(value = "getApplyList", method = RequestMethod.GET)
    @ResponseBody
    public List<ApplyPo> getApplyList(){
        return applyService.getApplyList();
    }

    /**
    * @description: 通过单位ID获取票据销毁申请列表
    * @param: [agenIdCode]
    * @return: java.util.List<com.bosssoft.ecds.entity.po.ApplyPo>
    * @date: 2020/8/16
    */
    @RequestMapping(value = "getApplyListByAgenIdCode",method = RequestMethod.GET)
    @ResponseBody
    public List<ApplyPo> getApplyListByAgenIdCode(String agenIdCode){
        return applyService.getApplyListByAgenIdCode(agenIdCode);
    }

    @RequestMapping(value = "getApplyInfoByDestroyNo", method = RequestMethod.GET)
    @ResponseBody
    public ApplyPo getApplyInfoByDestroyNo(String fDestroyNo){
        return applyService.getApplyInfoByDestroyNo(fDestroyNo);
    }

    @RequestMapping(value = "getItemListByDestroyNo",method = RequestMethod.GET)
    @ResponseBody
    public List<ItemPo> getItemListByDestroyNo(String fDestroyNo) {
        return itemService.getItemListByDestroyNo(fDestroyNo);
    }

    @RequestMapping(value = "deleteApplyInfoByDestroyNo",method = RequestMethod.GET)
    @ResponseBody
    public int deleteApplyInfoByDestroyNo(String fDestroyNo) {
        return applyService.deleteApplyInfoByDestroyNo(fDestroyNo);
    }

    @RequestMapping(value = "deleteItemInfoByDestroyNo",method = RequestMethod.GET)
    public int deleteItemInfoByDestroyNo(String fDestroyNo) {
        return itemService.deleteItemInfoByDestroyNo(fDestroyNo);
    }

    @RequestMapping(value = "updateApplyInfo",method = RequestMethod.GET)
    public int updateApplyInfo(ApplyVo applyVo){
        applyService.updateApplyInfo(applyVo.getApplyDto());
        itemService.updateItemInfo(applyVo.getItemDtoList());
        return 0;
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(String test){
        return test;
    }


    }
