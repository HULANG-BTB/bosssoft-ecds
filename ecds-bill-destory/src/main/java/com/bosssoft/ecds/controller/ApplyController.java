package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.po.ApplyPo;
import com.bosssoft.ecds.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: qiuheng
 * @create: 2020-08-12 15:22
 **/
@RestController
@RequestMapping(value = "apply")
public class ApplyController {
    @Autowired
    ApplyService applyService;
    @RequestMapping(value = "selectById")
    public ApplyPo selectById(Long id){
        return applyService.selectById(id);
    }

}
