package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.dto.UnitWriteOffApplyQueryInfoDTO;
import com.bosssoft.ecds.entity.dto.WriteOffApplyDTO;
import com.bosssoft.ecds.entity.vo.UnitWriteOffApplyQueryInfoVO;
import com.bosssoft.ecds.entity.vo.WriteOffApplyVO;
import com.bosssoft.ecds.service.UnitWriteOffService;
import com.bosssoft.ecds.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 单位段的核销服务
 */
@RestController
@RequestMapping("/unit")
public class UnitWriteOffController {
    private final UnitWriteOffService unitWriteOffService;

    @Autowired
    public UnitWriteOffController(UnitWriteOffService unitWriteOffService) {
        this.unitWriteOffService = unitWriteOffService;
    }

    @GetMapping("selectApply")
    public String selectApply (UnitWriteOffApplyQueryInfoVO queryInfoVO) {
        UnitWriteOffApplyQueryInfoDTO queryInfoDTO = BeanUtil.copyProperties(queryInfoVO, UnitWriteOffApplyQueryInfoDTO.class);
        IPage<WriteOffApplyDTO> page = unitWriteOffService.selectApplyPage(queryInfoDTO);
        IPage<WriteOffApplyVO> data = Convert.convert(new TypeReference<IPage<WriteOffApplyVO>>() {}, page);
        data.setRecords(Convert.toList(WriteOffApplyVO.class, page.getRecords()));
        return ResponseUtils.getResponse(data, ResponseUtils.ResultType.OK);
    }
}
