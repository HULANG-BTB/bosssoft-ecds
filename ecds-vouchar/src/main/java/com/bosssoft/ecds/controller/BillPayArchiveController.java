package com.bosssoft.ecds.controller;


import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.BillPayDTO;
import com.bosssoft.ecds.entity.vo.BillPayVO;
import com.bosssoft.ecds.service.BillPayArchiveService;
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
@Api("缴销记录接口")
@RestController
@RequestMapping("/pay")
public class BillPayArchiveController {

    @Autowired
    private BillPayArchiveService billPayArchiveService;

    /**
     * 获取单位的缴款信息情况
     *
     * @return 单位的缴款信息情况
     */
    @GetMapping("/info/{agenCode}")
    public String info(@PathVariable("agenCode") String agencode) {
        List<BillPayDTO> billPayInfos = billPayArchiveService.getBillPayInfos(agencode);
        List<BillPayVO> res = new ArrayList<>();

        billPayInfos.forEach(
                item -> {
                    BillPayVO vo = BeanUtil.toBean(item, BillPayVO.class);
                    res.add(vo);
                }
        );

        return ResponseUtils.getResponse(res, ResponseUtils.ResultType.SUCCESS);
    }
}

