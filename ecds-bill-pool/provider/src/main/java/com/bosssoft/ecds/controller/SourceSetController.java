package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.SourceSetDto;
import com.bosssoft.ecds.entity.vo.SourceSetVo;
import com.bosssoft.ecds.service.SourceSetService;
import com.bosssoft.ecds.utils.BeanUtils;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@Api
@RestController
public class SourceSetController {

    private static final Logger logger = LoggerFactory.getLogger(SourceSetController.class);

    @Resource
    SourceSetService sourceSetService;

    @RequestMapping(value = "/setSource", method = RequestMethod.GET)
    public int setSource(@RequestBody @Valid SourceSetDto sourceSetDto) {
        return sourceSetService.updateSet(sourceSetDto);
    }

    @RequestMapping(value = "/retrieveSetList", method = RequestMethod.GET)
    public List<SourceSetVo> retrieveSetList() {
        List<SourceSetVo> list = BeanUtils.convertList(sourceSetService.retrieveSetList(), SourceSetVo.class);
        return list;
    }

    @RequestMapping(value = "/retrieveSetByCode", method = RequestMethod.GET)
    public SourceSetVo retrieveSetByCode(@RequestParam @Valid @Length(min = 8, max = 8, message = "票号编码不规范") String billTypeCode) {
        SourceSetVo sourceSetVo = BeanUtils.convertObject(sourceSetService.retrieveSetByCode(billTypeCode), SourceSetVo.class);
        return sourceSetVo;
    }
}
