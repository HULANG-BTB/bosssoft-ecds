package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.service.UnitWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujierong
 * @date 2020-8-12
 */
@RestController
@RequestMapping("/unit")
public class UnitWriteOffController {
    private final UnitWriteOffService unitWriteOffService;

    @Autowired
    public UnitWriteOffController(UnitWriteOffService unitWriteOffService) {
        this.unitWriteOffService = unitWriteOffService;
    }
}
