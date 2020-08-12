package com.bosssoft.ecds.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.dao.SubjectDao;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectVO;
import com.bosssoft.ecds.service.SubjectService;
import com.bosssoft.ecds.utils.MyBeanUtil;
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
 * @author wy
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    /**
     * 根据3级科目id返回其及其下4级科目
     *
     * @param pid
     * @return
     */
    @GetMapping("/getFourthByPID/{pid}")
    public QueryResponseResult getFourthByPID(@PathVariable long pid) {
        List<SubjectDTO> subjectDTOS = subjectService.getFourthByPID(pid);
        List<SubjectVO> subjectVOS = MyBeanUtil.copyListProperties(subjectDTOS, SubjectVO::new);
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, subjectVOS);
        return queryResponseResult;

    }
}

