package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.common.response.R;
import com.bosssoft.ecds.entity.dto.ItemAvailableDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.entity.vo.ItemAvailableVO;
import com.bosssoft.ecds.service.ItemArchiveService;
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
@Api("归档可用")
@RestController
@RequestMapping("/archive/item")
public class ItemAvailableArchiveController {

    @Autowired
    private ItemArchiveService itemArchiveService;

    /**
     * 获取单位的可用票据情况
     *
     * @return 单位的可用票据情况
     */
    @ApiOperation(value = "获取单位的可用票据情况")
    @ApiImplicitParam("查询参数对象")
    @PostMapping("/info")
    public R info(@RequestBody @ApiParam("查询参数对象") CommonQuery query) {
        PageDTO<ItemAvailableDTO> page = itemArchiveService.getItemAvailableInfos(query);
        List<ItemAvailableDTO> data = page.getData();
        List<ItemAvailableVO> vos = MyBeanUtil.copyListProperties(data, ItemAvailableVO::new);
        return R.ok().data("items", vos).data("total", page.getTotal()).message("单位的可用票据情况");
    }
}

