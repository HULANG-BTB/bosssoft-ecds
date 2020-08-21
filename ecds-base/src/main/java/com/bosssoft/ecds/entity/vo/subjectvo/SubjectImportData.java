package com.bosssoft.ecds.entity.vo.subjectvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.bosssoft.ecds.constant.CheckConstant;
import com.bosssoft.ecds.constant.SubjectConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author shkstart
 * @create 2020-08-18 14:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SubjectImportData", description="")
public class SubjectImportData{

    @NotBlank(message = SubjectConstant.Subject_YEAR_NOT_NULL)
    @ExcelProperty("年度")
    @ApiModelProperty(value = "年度")
    private String year;

    @NotBlank(message = SubjectConstant.Subject_CODE_NOT_NULL)
    @Pattern(regexp = CheckConstant.INTEGER_REGULAR, message = SubjectConstant.CODE_IS_INTEGER)
    @ExcelProperty("科目编码")
    @ApiModelProperty(value = "科目编码")
    private String code;

    @NotBlank(message = SubjectConstant.Subject_NAME_NOT_NULL)
    @Pattern(regexp = CheckConstant.CHINESE_REGULAR, message = SubjectConstant.NAME_IS_CHINESE)
    @ExcelProperty("科目名称")
    @ApiModelProperty(value = "科目名称")
    private String name;

    @ExcelProperty("备注")
    @ApiModelProperty(value = "备注")
    private String remark;
}
