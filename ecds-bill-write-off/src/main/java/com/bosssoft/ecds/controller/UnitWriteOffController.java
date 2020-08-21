package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.vo.*;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.QueryResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.UnitWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 单位段的核销服务
 */
@CrossOrigin
@RestController
@RequestMapping("/unit")
public class UnitWriteOffController {
    private final UnitWriteOffService unitWriteOffService;

    @Autowired
    public UnitWriteOffController(UnitWriteOffService unitWriteOffService) {
        this.unitWriteOffService = unitWriteOffService;
    }

    @GetMapping("selectApply")
    public ResponseResult selectApply (UnitWriteOffApplyQueryInfoVO queryInfoVO) {
        UnitWriteOffApplyQueryInfoDTO queryInfoDTO = BeanUtil.copyProperties(queryInfoVO, UnitWriteOffApplyQueryInfoDTO.class);
        IPage<WriteOffApplyDTO> page = unitWriteOffService.selectApplyPage(queryInfoDTO);
        List<WriteOffApplyVO> list = Convert.toList(WriteOffApplyVO.class, page.getRecords());
        transformApplyDTOToVO(list);
        QueryResult<WriteOffApplyVO> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(page.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

    @DeleteMapping("deleteApply/{no}")
    public ResponseResult deleteApply(@PathVariable String no) {
        if (unitWriteOffService.deleteApply(no)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.FAIL();
    }

    @PutMapping("uploadApply")
    public ResponseResult uploadApply(@RequestBody List<String> noList) {
        if (unitWriteOffService.uploadApply(noList)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.FAIL();
    }

    @PutMapping("rescindApply")
    public ResponseResult rescindApply(@RequestBody List<String> noList) {
        if (unitWriteOffService.rescindApply(noList)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.FAIL();
    }

    @GetMapping("selectItem")
    public ResponseResult selectItem(UnitWriteOffItemAndIncomeQueryInfoVO queryInfoVO) {
        UnitWriteOffItemAndIncomeQueryInfoDTO queryInfoDTO = BeanUtil.copyProperties(queryInfoVO, UnitWriteOffItemAndIncomeQueryInfoDTO.class);
        IPage<WriteOffApplyItemDTO> page = unitWriteOffService.selectItemPage(queryInfoDTO);
        QueryResult<WriteOffApplyItemVO> queryResult = new QueryResult<>();
        queryResult.setTotal(page.getTotal());
        queryResult.setList(Convert.toList(WriteOffApplyItemVO.class, page.getRecords()));
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

    @GetMapping("selectIncome")
    public ResponseResult selectIncome(UnitWriteOffItemAndIncomeQueryInfoVO queryInfoVO) {
        UnitWriteOffItemAndIncomeQueryInfoDTO queryInfoDTO = BeanUtil.copyProperties(queryInfoVO, UnitWriteOffItemAndIncomeQueryInfoDTO.class);
        IPage<WriteOffApplyIncomeDTO> page = unitWriteOffService.selectIncomePage(queryInfoDTO);
        QueryResult<WriteOffApplyIncomeVO> queryResult = new QueryResult<>();
        queryResult.setTotal(page.getTotal());
        queryResult.setList(Convert.toList(WriteOffApplyIncomeVO.class, page.getRecords()));
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

    @GetMapping("getBillInfo")
    public ResponseResult getBillInfo(BillQueryVO billQueryVO) {
        BillQueryDTO billQueryDTO = BeanUtil.copyProperties(billQueryVO, BillQueryDTO.class);
        BillInfoDTO billInfoDTO = unitWriteOffService.getData(billQueryDTO);
        if (billInfoDTO == null) {
            return ResponseResult.FAIL();
        }
        BillInfoVO billInfoVO = new BillInfoVO();
        billInfoVO.setApplyItemVOS(Convert.toList(WriteOffApplyItemVO.class, billInfoDTO.getApplyItemDTOS()));
        billInfoVO.setApplyIncomeVOS(Convert.toList(WriteOffApplyIncomeVO.class, billInfoDTO.getApplyIncomeDTOS()));
        return new QueryResponseResult<>(CommonCode.SUCCESS, billInfoVO);
    }

    @PostMapping("addOrUpdateApply")
    public ResponseResult addOrUpdateApply(@RequestBody ApplyVO applyVO) {
        ApplyDTO applyDTO = BeanUtil.copyProperties(applyVO, ApplyDTO.class);
        applyDTO.getBillInfo().setApplyItemDTOS(Convert.toList(WriteOffApplyItemDTO.class, applyVO.getBillInfo().getApplyItemVOS()));
        applyDTO.getBillInfo().setApplyIncomeDTOS(Convert.toList(WriteOffApplyIncomeDTO.class, applyVO.getBillInfo().getApplyIncomeVOS()));
        if (unitWriteOffService.addOrUpdateApply(applyDTO)) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.FAIL();
    }
    
    private void transformApplyDTOToVO(List<WriteOffApplyVO> list) {
        // 将对应的数字代码转换为字符串
        for (WriteOffApplyVO item : list){
            switch (item.getfChangeState()) {
                case "1" :
                    item.setfChangeState("未审验");
                    break;
                case "2" :
                    item.setfChangeState("已审验");
                    break;
                default:
                    break;
            }
            if (item.getfCheckResult() != null) {
                switch (item.getfCheckResult()) {
                    case "1" :
                        item.setfCheckResult("良好");
                        break;
                    case "2" :
                        item.setfCheckResult("合格");
                        break;
                    case "3" :
                        item.setfCheckResult("问题");
                        break;
                    case "4" :
                        item.setfCheckResult("整改通过");
                        break;
                    default:
                        break;
                }
            }
            switch (item.getfIsUpload()) {
                case "1" :
                    item.setfIsUpload("未上报");
                    break;
                case "2" :
                    item.setfIsUpload("已上报");
                    break;
                default:
                    break;
            }
        }
    }
}
