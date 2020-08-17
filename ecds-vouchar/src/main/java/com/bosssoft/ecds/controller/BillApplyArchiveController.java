package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
import com.bosssoft.ecds.entity.vo.BillApplyVO;
import com.bosssoft.ecds.service.BillApplyArchiveService;
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
 * 向前端展示票据申领的数据
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Api("归档领用")
@RestController
@RequestMapping("/apply")
public class BillApplyArchiveController {

    @Autowired
    BillApplyArchiveService service;

    /**
     * 获取单位的票据领用情况
     *
     * @return 单位端的票据领用情况
     */
    @GetMapping("/info/{agenCode}")
    public String info(@PathVariable("agenCode") String agencode) {
        List<BillApplyDTO> billApplyInfo = service.getBillApplyInfo(agencode);
        List<BillApplyVO> res = new ArrayList<>();

        billApplyInfo.forEach(
                item -> {
                    BillApplyVO vo = BeanUtil.toBean(item, BillApplyVO.class);
                    res.add(vo);
                }
        );

        return ResponseUtils.getResponse(res, ResponseUtils.ResultType.SUCCESS);
    }
}

