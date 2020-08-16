package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author wy
 * @create 2020-08-10 20:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_income_sort_subject")
@ApiModel(value="IncomeSortSubjectDTO对象", description="")
public class IncomeSortSubjectDTO extends Model<IncomeSortSubjectDTO> {
    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "收入类别id")
    @TableField("f_income_sort_id")
    private Long incomeSortId;

    @ApiModelProperty(value = "预算科目id")
    @TableField("f_subject_id")
    private Long subjectId;

    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;
}
