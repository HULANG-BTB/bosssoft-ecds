package com.bosssoft.ecds.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.dao.SubjectDao;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectVO;
import com.bosssoft.ecds.service.SubjectService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "预算科目接口")
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @ApiOperation(value = "根据3级科目id返回其及其下4级科目")
    @GetMapping("/getFourthByPID/{pid}")
    public QueryResponseResult getFourthByPID(@PathVariable long pid) {
        List<SubjectDTO> subjectDTOS = subjectService.getFourthByPID(pid);
        List<SubjectVO> subjectVOS = MyBeanUtil.copyListProperties(subjectDTOS, SubjectVO::new);
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, subjectVOS);
        return queryResponseResult;

    }

    @ApiOperation(value = "添加预算科目",notes = "类别名称和编码唯一")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody @Validated SubjectVO subjectVO){
        SubjectDTO subjectDTO = MyBeanUtil.copyProperties(subjectVO, SubjectDTO.class);
        subjectService.add(subjectDTO);
        return new ResponseResult(CommonCode.SUCCESS);

    }
}

