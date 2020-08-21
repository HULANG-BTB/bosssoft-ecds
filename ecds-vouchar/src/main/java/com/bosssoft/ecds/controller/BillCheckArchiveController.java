package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.common.response.R;
import com.bosssoft.ecds.entity.dto.BillCheckDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.BillCheckVO;
import com.bosssoft.ecds.service.BillCheckArchiveService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        PageDTO<BillCheckDTO> pageDTO = billCheckArchiveService.getBillCheckInfos(query);
        // 获取数据并且转换对象
        List<BillCheckDTO> data = pageDTO.getData();
        List<BillCheckVO> vos = MyBeanUtil.copyListProperties(data, BillCheckVO::new);
        return R.ok().data("items", vos).data("total", pageDTO.getTotal()).message("单位的票据审验情况");
    }
}
