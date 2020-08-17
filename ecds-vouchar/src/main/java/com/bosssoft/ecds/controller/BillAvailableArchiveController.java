package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.BillAvailableInfoDTO;
import com.bosssoft.ecds.entity.vo.BillAvailableVO;
import com.bosssoft.ecds.service.BillAvailableArchiveService;
import com.bosssoft.ecds.utils.ResponseUtils;
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
 * @since 2020-08-13
 */
@RestController
@RequestMapping("/available")
public class BillAvailableArchiveController {

    @Autowired
    private BillAvailableArchiveService billAvailableArchiveService;

    /**
     * 获取单位的可用票据情况
     *
     * @return 单位的可用票据情况
     */
    @GetMapping("/info/{agenCode}")
    public String info(@PathVariable("agenCode") String agencode) {
        List<BillAvailableInfoDTO> billApplyInfos = billAvailableArchiveService.getBillApplyInfos(agencode);
        List<BillAvailableVO> res = new ArrayList<>();

        billApplyInfos.forEach(
                item -> {
                    BillAvailableVO vo = BeanUtil.toBean(item, BillAvailableVO.class);
                    res.add(vo);
                }
        );

        return ResponseUtils.getResponse(res, ResponseUtils.ResultType.SUCCESS);
    }

}

