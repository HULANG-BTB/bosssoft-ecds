package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.SourceSetDto;
import com.bosssoft.ecds.entity.vo.SourceSetVo;
import com.bosssoft.ecds.service.SourceSetService;
import com.bosssoft.ecds.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/setPool")
@CrossOrigin
public class SourceSetController {

    private static final Logger logger = LoggerFactory.getLogger(SourceSetController.class);

    @Resource
    SourceSetService sourceSetService;

    /**
     * 新增、修改、删除
     * @param sourceSetDto
     * @return
     */
    @RequestMapping("/setSource")
    public int setSource(@RequestBody SourceSetDto sourceSetDto) {
        return sourceSetService.updateSet(sourceSetDto);
    }

    /**
     * 获取
     * @return
     */
    @RequestMapping("/retrieveSetList")
    public List<SourceSetVo> retrieveSetList() {
        List<SourceSetVo> list = BeanUtils.convertList(sourceSetService.retrieveSetList(), SourceSetVo.class);
        return list;
    }

    /**
     * 根据票据代码获取
     * @param billTypeCode
     * @return
     */
    @RequestMapping("/retrieveSetByCode")
    public SourceSetVo retrieveSetByCode(@RequestParam String billTypeCode) {
        SourceSetVo sourceSetVo = BeanUtils.convertObject(sourceSetService.retrieveSetByCode(billTypeCode), SourceSetVo.class);
        return sourceSetVo;
    }
}
