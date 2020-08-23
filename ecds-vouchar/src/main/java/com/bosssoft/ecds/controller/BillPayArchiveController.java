package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.BillPayDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.BillPayVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.BillPayArchiveService;
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
@Api("缴销记录接口")
@Slf4j(topic = "kafka_logger")
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
    public QueryResponseResult<PageVO<BillPayVO>> info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        PageVO<BillPayVO> res = new PageVO<>();
        PageDTO<BillPayDTO> page = billPayArchiveService.getBillPayInfos(query);
        List<BillPayDTO> data = page.getData();
        List<BillPayVO> vos = MyBeanUtil.copyListProperties(data, BillPayVO::new);
        res.setItems(vos);
        res.setTotal(page.getTotal());
        log.info("" + res);
        log.info("res :" + new QueryResponseResult<>(CommonCode.SUCCESS, res));
        return new QueryResponseResult<>(CommonCode.SUCCESS, res);
    }
}

