package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.BillWarnDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.BillWarnVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.BillWarnArchiveService;
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
 * 前端控制器
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Api("归档预警接口")
@Slf4j(topic = "kafka_logger")
@RestController
@RequestMapping("/archive/warn")
public class BillWarnArchiveController {

    @Autowired
    private BillWarnArchiveService billWarnArchiveService;

    /**
     * 获取单位的预警信息情况
     *
     * @return 单位的预警信息情况
     */
    @ApiOperation(value = "获取单位的预警信息情况")
    @ApiImplicitParam("查询参数对象")
    @PostMapping("/info")
    public QueryResponseResult<PageVO<BillWarnVO>> info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        PageVO<BillWarnVO> res = new PageVO<>();
        PageDTO<BillWarnDTO> page = billWarnArchiveService.getBillWarnInfos(query);
        List<BillWarnDTO> data = page.getData();
        List<BillWarnVO> vos = MyBeanUtil.copyListProperties(data, BillWarnVO::new);
        res.setItems(vos);
        res.setTotal(page.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS, res);
    }
}

