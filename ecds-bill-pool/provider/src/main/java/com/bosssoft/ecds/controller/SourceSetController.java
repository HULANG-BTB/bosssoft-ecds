package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.SourceSetDto;
import com.bosssoft.ecds.entity.vo.SourceSetVo;
import com.bosssoft.ecds.service.SourceSetService;
import com.bosssoft.ecds.utils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SourceSetController {

    @Resource
    SourceSetService sourceSetService;

    @RequestMapping("/setMin")
    public int setMin(@RequestBody SourceSetDto sourceSetDto) {
        return sourceSetService.updateMin(sourceSetDto);
    }

    @RequestMapping("/setPushNumber")
    public int setPushNumber(@RequestBody SourceSetDto sourceSetDto) {
        return sourceSetService.updatePushNumber(sourceSetDto);
    }

    @RequestMapping("/retrieveSetList")
    public List<SourceSetVo> retrieveSetList() {
        List<SourceSetVo> list = BeanUtils.convertList(sourceSetService.retrieveSetList(), SourceSetVo.class);
        return list;
    }
}
