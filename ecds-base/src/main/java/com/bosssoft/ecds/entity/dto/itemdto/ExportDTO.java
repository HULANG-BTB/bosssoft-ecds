package com.bosssoft.ecds.entity.dto.itemdto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 吴志鸿
 * @date 2020/8/19
 * @description
 */
@Data
public class ExportDTO extends BaseRowModel {
    @ExcelProperty(value = "主键",index = 0)
    private Long id;

    @ExcelProperty(value = "项目编码",index = 1)
    private String itemId;

    @ExcelProperty(value = "项目名称",index = 2)
    private String itemName;

    @ExcelProperty(value = "助记码",index = 3)
    private String mnen;

    @ExcelProperty(value = "项目生效日期",index = 4)
    private Date itemEffdate;

    @ExcelProperty(value = "项目失效日期",index = 5)
    private Date itemExpdate;

    @ExcelProperty(value = "记录生效日期",index = 6)
    private Date effdate;

    @ExcelProperty(value = "记录截止日期",index = 7)
    private Date expdate;

    @ExcelProperty(value = "是否启用",index = 8)
    private Integer isenable;

    @ExcelProperty(value = "收入类别",index = 9)
    private String incomSortCode;

    @ExcelProperty(value = "资金性质",index = 10)
    @ApiModelProperty(value = "")
    private String fundsnatureCode;

    @ExcelProperty(value = "预算科目",index = 11)
    private String subject;

    @ExcelProperty(value = "预算科目名字",index =12)
    private String subjectName;

    @ExcelProperty(value = "收缴方式",index = 13)
    private String paymode;

    @ExcelProperty(value = "经办人",index = 14)
    private String operator;

    @ExcelProperty(value = "经办人id",index = 15)
    private Long operatorId;

    @ExcelProperty(value = "创建时间",index = 16)
    private Date createTime;

    @ExcelProperty(value = "最后修改时间",index = 17)
    private Date updateTime;

    @ExcelProperty(value = "备注",index = 18)
    private String note;
}
