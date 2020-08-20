package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.common.response.R;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.BillApplyVO;
import com.bosssoft.ecds.service.BillApplyArchiveService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
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
    public R info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        PageDTO<BillApplyDTO> pageDTO = service.getBillApplyInfo(query);
        List<BillApplyVO> billApplyVOS = MyBeanUtil.copyListProperties(pageDTO.getData(), BillApplyVO::new);
        log.info("con " + billApplyVOS);
        return R.ok().data("items", billApplyVOS).data("total", pageDTO.getTotal()).message("票据申领情况。");
    }
}

