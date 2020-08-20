package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.BillApplyVO;
import com.bosssoft.ecds.service.BillApplyArchiveService;
import com.bosssoft.ecds.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 向前端展示票据申领的数据
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Api("归档领用")
@RestController
@RequestMapping("/archive/apply")
public class BillApplyArchiveController {

    @Autowired
    BillApplyArchiveService service;

    /**
     * 获取单位的票据领用情况
     *
     * @return 单位端的票据领用情况
     */
    @ApiOperation(value = "获取单位的票据领用情况")
    @ApiImplicitParam("查询参数对象")
    @PostMapping("/info")
    public String info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        List<BillApplyDTO> billApplyInfo = service.getBillApplyInfo(query);
        List<BillApplyVO> res = new ArrayList<>();

        billApplyInfo.forEach(
                item -> {
                    BillApplyVO vo = BeanUtil.toBean(item, BillApplyVO.class);
                    res.add(vo);
                }
        );

        return ResponseUtils.getResponse(res, ResponseUtils.ResultType.SUCCESS);
    }
}

