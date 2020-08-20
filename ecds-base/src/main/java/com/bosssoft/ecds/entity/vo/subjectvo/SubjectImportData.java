package com.bosssoft.ecds.entity.vo.subjectvo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author shkstart
 * @create 2020-08-18 14:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SubjectImportData", description="")
public class SubjectImportData{
    @ExcelProperty("年度")
    @ApiModelProperty(value = "年度")
    private String year;

    @ExcelProperty("科目编码")
    @ApiModelProperty(value = "科目编码")
    private String code;

    @ExcelProperty("科目名称")
    @ApiModelProperty(value = "科目名称")
    private String name;

    @ExcelProperty("备注")
    @ApiModelProperty(value = "备注")
    private String remark;
}
