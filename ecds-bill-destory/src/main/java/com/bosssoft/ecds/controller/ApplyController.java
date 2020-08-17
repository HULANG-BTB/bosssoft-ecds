package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.ApplyDto;
import com.bosssoft.ecds.entity.dto.ItemDto;
import com.bosssoft.ecds.entity.po.ApplyPo;
import com.bosssoft.ecds.entity.vo.ApplyVo;
import com.bosssoft.ecds.service.ApplyService;
import com.bosssoft.ecds.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * @author: qiuheng
     * @date: 2020/8/13
     */
    @RequestMapping(value = "insertApplyInfo", method = RequestMethod.POST)
    @ResponseBody
    public String insertApplyInfo(@RequestBody ApplyVo applyVo) {
        applyService.insertApplyInfo(applyVo.getApplyDto());
        itemService.insertItemInfo(applyVo.getItemDtoList(), applyVo.getApplyDto().getfDestroyNo());
        return null;
    }

    @RequestMapping(value = "getApplyList", method = RequestMethod.GET)
    @ResponseBody
    public List<ApplyPo> getApplyList(){
        return applyService.getApplyList();
    }

}
