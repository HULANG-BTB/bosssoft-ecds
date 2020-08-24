package com.bosssoft.ecds.entity.vo.subjectvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author shkstart
 * @create 2020-08-17 14:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SubjectExcelData", description="")
public class SubjectExcelData extends Model<SubjectExcelData> {

    @ExcelProperty("科目id")
    @NumberFormat("#.####")
    @ApiModelProperty(value = "科目id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

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

    @ExcelProperty("层级")
    @ApiModelProperty(value = "层级")
    private Integer level;

    @ExcelProperty("是否底级")
    @ApiModelProperty(value = "是否底级,0否，1是")
    private String leaf;


    @ExcelProperty("父级id")
    @NumberFormat("#.####")
    @ApiModelProperty(value = "父级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @ExcelProperty("是否启用")
    @ApiModelProperty(value = "是否启用，0否，1是")
    private String enable;


    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("创建时间")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("更新时间")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ExcelProperty("操作人id")
    @NumberFormat("#.####")
    @ApiModelProperty(value = "操作人id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long operatorId;

    @ExcelProperty("操作人")
    @ApiModelProperty(value = "操作人")
    private String operator;
}
