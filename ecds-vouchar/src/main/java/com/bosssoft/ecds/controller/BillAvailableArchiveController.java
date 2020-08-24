package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.BillAvailableInfoDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.BillAvailableVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.BillAvailableArchiveService;
import com.bosssoft.ecds.utils.MyBeanUtil;
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
 * @since 2020-08-13
 */
@Slf4j(topic = "kafka_logger")
@RestController
@RequestMapping("/archive/available")
public class BillAvailableArchiveController {

    @Autowired
    private BillAvailableArchiveService billAvailableArchiveService;

    /**
     * 获取单位的可用票据情况
     *
     * @return 单位的可用票据情况
     */
    @ApiOperation(value = "获取单位的可用票据情况")
    @ApiImplicitParam("查询参数对象")
    @PostMapping("/info")
    public QueryResponseResult<PageVO<BillAvailableVO>> info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        PageVO<BillAvailableVO> res = new PageVO<>();
        PageDTO<BillAvailableInfoDTO> pageDto = billAvailableArchiveService.getBillApplyInfos(query);
        List<BillAvailableInfoDTO> data = pageDto.getData();
        List<BillAvailableVO> vos = MyBeanUtil.copyListProperties(data, BillAvailableVO::new);
        //添加条件
        res.setItems(vos);
        res.setTotal(pageDto.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS, res);
    }

}

