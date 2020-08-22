package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.dto.StockOutChangeDto;
import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.entity.vo.OutChangePageVo;
import com.bosssoft.ecds.entity.vo.StockOutPageVo;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.StockOutnoticeChangeService;
import com.bosssoft.ecds.util.ConverUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Slf4j
@RestController
@RequestMapping("/out-change")
//@CrossOrigin
@Api(tags = "财政出库变动Controller")
public class StockOutnoticeChangeController extends BaseController {

    @Autowired
    private StockOutnoticeChangeService changeService;

    /**
     * 展示所有变动记录
     *
     * @return 变动记录的list
     */
    @ApiOperation("展示变动记录列表")
    @PostMapping("/showAll")
    public QueryResponseResult<PageResult> showAll(@Valid @RequestBody OutChangePageVo pageVo) {
        log.info("进入showAll方法...");
        Long total = changeService.getCount(pageVo);
        List<StockOutChangeDto> changeDtos = changeService.queryByPageVo(
                pageVo,
                pageVo.getPage(),
                pageVo.getLimit());

        // 封装结果集，携带页面参数
        PageResult pageResult = new PageResult(
                total,
                pageVo.getLimit(),
                pageVo.getPage(),
                changeDtos);
        log.info("退出方法，data:{}",changeDtos.toString());
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageResult);
    }

    /**
     * 删除多选list
     *
     * @param changeDtos 要删除的变动dto的list
     *
     * @return 是否成功
     */
    @ApiOperation("批量删除变动记录")
    @PutMapping("/deleteAll")
    public ResponseResult deleteAll(@RequestBody List<StockOutChangeDto> changeDtos) {
        Boolean result = changeService.deleteByPos(changeDtos);
        return getRes(result);
    }

}

