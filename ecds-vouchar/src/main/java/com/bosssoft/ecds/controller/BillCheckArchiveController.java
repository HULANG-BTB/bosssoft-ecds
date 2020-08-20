package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.BillCheckDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.BillCheckVO;
import com.bosssoft.ecds.service.BillCheckArchiveService;
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
@Api(value = "审验记录接口")
@RestController
@RequestMapping("/archive/check")
public class BillCheckArchiveController {

    @Autowired
    private BillCheckArchiveService billCheckArchiveService;

    /**
     * 获取单位的票据审验情况
     *
     * @return 单位的票据审验情况
     */
    @ApiOperation(value = "获取单位的票据审验情况")
    @ApiImplicitParam("查询参数对象")
    @PostMapping("/info")
    public String info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        List<BillCheckDTO> billCheckInfos = billCheckArchiveService.getBillCheckInfos(query);
        List<BillCheckVO> res = new ArrayList<>();

        billCheckInfos.forEach(
                item -> {
                    BillCheckVO vo = BeanUtil.toBean(item, BillCheckVO.class);
                    res.add(vo);
                }
        );

        return ResponseUtils.getResponse(res, ResponseUtils.ResultType.SUCCESS);
    }
}

