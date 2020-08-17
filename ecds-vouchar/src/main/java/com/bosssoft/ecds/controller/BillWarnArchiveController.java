package com.bosssoft.ecds.controller;


import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.BillWarnDTO;
import com.bosssoft.ecds.entity.vo.BillWarnVO;
import com.bosssoft.ecds.service.BillWarnArchiveService;
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
@Api("归档预警接口")
@RestController
@RequestMapping("/warn")
public class BillWarnArchiveController {

    @Autowired
    private BillWarnArchiveService billWarnArchiveService;

    /**
     * 获取单位的预警信息情况
     *
     * @return 单位的预警信息情况
     */
    @GetMapping("/info/{agenCode}")
    public String info(@PathVariable("agenCode") String agencode) {
        List<BillWarnDTO> billWarnInfos = billWarnArchiveService.getBillWarnInfos(agencode);
        List<BillWarnVO> res = new ArrayList<>();

        billWarnInfos.forEach(
                item -> {
                    BillWarnVO vo = BeanUtil.toBean(item, BillWarnVO.class);
                    res.add(vo);
                }
        );

        return ResponseUtils.getResponse(res, ResponseUtils.ResultType.SUCCESS);
    }
}

