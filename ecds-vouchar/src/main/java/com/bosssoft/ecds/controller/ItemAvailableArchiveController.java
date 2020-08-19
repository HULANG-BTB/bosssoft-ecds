package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.ItemAvailableDTO;
import com.bosssoft.ecds.entity.vo.ItemAvailableVO;
import com.bosssoft.ecds.service.ItemArchiveService;
import com.bosssoft.ecds.utils.ResponseUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api("归档可用")
@RestController
@RequestMapping("/item")
public class ItemAvailableArchiveController {

    @Autowired
    private ItemArchiveService itemArchiveService;

    /**
     * 获取单位的可用票据情况
     *
     * @return 单位的可用票据情况
     */
    @GetMapping("/info/{agenCode}")
    public String info(@PathVariable("agenCode") String agencode) {
        List<ItemAvailableDTO> itemAvailableInfos = itemArchiveService.getItemAvailableInfos(agencode);
        List<ItemAvailableVO> res = new ArrayList<>();

        itemAvailableInfos.forEach(
                item -> {
                    ItemAvailableVO vo = BeanUtil.toBean(item, ItemAvailableVO.class);
                    res.add(vo);
                }
        );

        return ResponseUtils.getResponse(res, ResponseUtils.ResultType.SUCCESS);
    }
}

