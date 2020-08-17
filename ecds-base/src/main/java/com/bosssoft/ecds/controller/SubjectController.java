package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectQueryVO;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectVO;
import com.bosssoft.ecds.entity.vo.subjectvo.UpdateSubjectVO;
import com.bosssoft.ecds.service.SubjectService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "分页条件查询接口")
    @PostMapping("/listPage")
    public QueryResponseResult listPage(@RequestBody @Validated SubjectQueryVO subjectQueryVO) {
        return subjectService.listPage(subjectQueryVO);
    }

    @ApiOperation(value = "添加预算科目",notes = "只传code,name,parentId,year四个字段")
    @PostMapping("/add")
    public QueryResponseResult add(@RequestBody @Validated SubjectVO subjectVO){
        SubjectDTO subjectDTO = MyBeanUtil.copyProperties(subjectVO, SubjectDTO.class);
        return subjectService.add(subjectDTO);
    }


    @ApiOperation(value = "修改预算科目")
    @PostMapping("/update")
    public QueryResponseResult update(@RequestBody @Validated UpdateSubjectVO updateSubjectVO){
        return subjectService.update(updateSubjectVO);
    }

    @ApiOperation(value = "删除预算科目")
    @PostMapping("/delete")
    public QueryResponseResult update(Long id){
        return subjectService.delete(id);
    }

    @ApiOperation(value = "复制预算科目,需要选中左侧1级树形菜单且来源和目标年度，用户不可更改，目标年度为今年，调试请复制2019年的代管收入")
    @PostMapping("/copy")
    public QueryResponseResult copy(Long id){
        return subjectService.copy(id);
    }
}

