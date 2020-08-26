package com.bosssoft.ecds.controller;

import com.alibaba.excel.EasyExcel;
import com.bosssoft.ecds.constant.SubjectConstant;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.vo.subjectvo.*;
import com.bosssoft.ecds.service.SubjectService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

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

    @ApiOperation(value = "添加预算科目", notes = "只传code,name,parentId,year四个字段")
    @PostMapping("/add")
    public QueryResponseResult add(@RequestBody @Validated SubjectVO subjectVO) {
        SubjectDTO subjectDTO = MyBeanUtil.copyProperties(subjectVO, SubjectDTO.class);
        return subjectService.add(subjectDTO);
    }


    @ApiOperation(value = "修改预算科目")
    @PostMapping("/update")
    public QueryResponseResult update(@RequestBody @Validated UpdateSubjectVO updateSubjectVO) {
        return subjectService.update(updateSubjectVO);
    }

    @ApiOperation(value = "删除预算科目")
    @PostMapping("/delete")
    public QueryResponseResult delete(@RequestBody @Validated UpdateSubjectVO deleteSubjectVO) {
        return subjectService.delete(deleteSubjectVO.getId());
    }

    @ApiOperation(value = "复制预算科目,需要选中左侧1级树形菜单且来源和目标年度，用户不可更改，目标年度为今年")
    @PostMapping("/copy")
    public QueryResponseResult copy(@RequestBody @Validated UpdateSubjectVO copySubjectVO) {
        return subjectService.copy(copySubjectVO.getId());
    }

    @ApiOperation(value = "导出预算科目", notes = "页数1，每页大小65535")
    @PostMapping("/download")
    public void download(@RequestBody @Validated SubjectQueryVO subjectQueryVO, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode(subjectService.getFileName(subjectQueryVO.getId()), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + "."+ SubjectConstant.FILE_suffix);
        EasyExcel.write(response.getOutputStream(), SubjectExcelData.class).sheet("预算科目").doWrite(subjectService.selectExcel(subjectQueryVO));
    }

    @ApiOperation(value = "导入预算科目")
    @PostMapping("/upload")
    public QueryResponseResult upload(MultipartFile file, Long id) throws IOException {
        return subjectService.upload(file, id);
    }
}

