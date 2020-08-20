package com.bosssoft.ecds.controller;


import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.BillPayDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.BillPayVO;
import com.bosssoft.ecds.service.BillPayArchiveService;
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
 * 前端控制器
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Api("缴销记录接口")
@RestController
@RequestMapping("/archive/pay")
public class BillPayArchiveController {

    @Autowired
    private BillPayArchiveService billPayArchiveService;

    /**
     * 获取单位的缴款信息情况
     *
     * @return 单位的缴款信息情况
     */
    @ApiOperation(value = "获取单位的缴款信息情况")
    @ApiImplicitParam("查询参数对象")
    @PostMapping("/info")
    public String info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        List<BillPayDTO> billPayInfos = billPayArchiveService.getBillPayInfos(query);
        List<BillPayVO> res = new ArrayList<>();

        billPayInfos.forEach(
                item -> {
                    BillPayVO vo = BeanUtil.toBean(item, BillPayVO.class);
                    res.add(vo);
                }
        );

        return ResponseUtils.getResponse(res, ResponseUtils.ResultType.SUCCESS);
    }
}

