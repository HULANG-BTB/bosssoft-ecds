package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.vo.*;
import com.bosssoft.ecds.service.UnitWriteOffService;
import com.bosssoft.ecds.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 单位段的核销服务
 */
@CrossOrigin
@RestController
@RequestMapping("/unit")
@Slf4j
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
        List<WriteOffApplyVO> list = Convert.toList(WriteOffApplyVO.class, page.getRecords());
        transformApplyDTOToVO(list);
        data.setRecords(list);
        return ResponseUtils.getResponse(data, ResponseUtils.ResultType.OK);
    }

    @DeleteMapping("deleteApply/{no}")
    public String deleteApply(@PathVariable String no) {
        if (unitWriteOffService.deleteApply(no)) {
            return ResponseUtils.getResponse(ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(601, "删除失败");
    }

    @PutMapping("uploadApply")
    public String uploadApply(@RequestBody List<String> noList) {
        if (unitWriteOffService.uploadApply(noList)) {
            return ResponseUtils.getResponse(ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(601, "上报失败");
    }

    @PutMapping("rescindApply")
    public String rescindApply(@RequestBody List<String> noList) {
        if (unitWriteOffService.rescindApply(noList)) {
            return ResponseUtils.getResponse(ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(601, "无法撤销");
    }

    @GetMapping("selectItem")
    public String selectItem(UnitWriteOffItemQueryInfoVO queryInfoVO) {
        UnitWriteOffItemQueryInfoDTO queryInfoDTO = BeanUtil.copyProperties(queryInfoVO, UnitWriteOffItemQueryInfoDTO.class);
        IPage<WriteOffApplyItemDTO> page = unitWriteOffService.selectItemPage(queryInfoDTO);
        IPage<WriteOffApplyItemVO> data = Convert.convert(new TypeReference<IPage<WriteOffApplyItemVO>>() {}, page);
        data.setRecords(Convert.toList(WriteOffApplyItemVO.class, page.getRecords()));
        return ResponseUtils.getResponse(data, ResponseUtils.ResultType.OK);
    }

    @GetMapping("getBillInfo")
    public String getBillInfo(BillQueryVO billQueryVO) {
        BillQueryDTO billQueryDTO = BeanUtil.copyProperties(billQueryVO, BillQueryDTO.class);
        BillInfoDTO billInfoDTO = unitWriteOffService.getData(billQueryDTO);
        BillInfoVO billInfoVO = new BillInfoVO();
        billInfoVO.setApplyItemVOS(Convert.toList(WriteOffApplyItemVO.class, billInfoDTO.getApplyItemDTOS()));
        billInfoVO.setApplyIncomeVOS(Convert.toList(WriteOffApplyIncomeVO.class, billInfoDTO.getApplyIncomeDTOS()));
        return ResponseUtils.getResponse(billInfoVO, ResponseUtils.ResultType.OK);
    }
    
    private void transformApplyDTOToVO(List<WriteOffApplyVO> list) {
        // 将对应的数字代码转换为字符串
        for (WriteOffApplyVO item : list){
            switch (item.getFChangeState()) {
                case "1" :
                    item.setFChangeState("未审验");
                    break;
                case "2" :
                    item.setFChangeState("已审验");
                    break;
                default:
                    break;
            }
            if (item.getFCheckResult() != null) {
                switch (item.getFCheckResult()) {
                    case "1" :
                        item.setFCheckResult("良好");
                        break;
                    case "2" :
                        item.setFCheckResult("合格");
                        break;
                    case "3" :
                        item.setFCheckResult("问题");
                        break;
                    case "4" :
                        item.setFCheckResult("整改通过");
                        break;
                    default:
                        break;
                }
            }
            switch (item.getFIsUpload()) {
                case "1" :
                    item.setFIsUpload("未上报");
                    break;
                case "2" :
                    item.setFIsUpload("已上报");
                    break;
                default:
                    break;
            }
        }
    }
}
