package com.bosssoft.ecds.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;
import java.util.List;

/**
 * @author wy
 * @create 2020-08-10 20:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_subject")
@ApiModel(value="SubjectVO对象", description="")
public class SubjectVO extends Model<SubjectVO> {
    @ApiModelProperty(value = "科目id")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "年度")
    @TableField("f_year")
    private String year;

    @ApiModelProperty(value = "科目编码")
    @TableField("f_sub_code")
    private String subCode;

    @ApiModelProperty(value = "科目名称")
    @TableField("f_sub_name")
    private String subName;

    @ApiModelProperty(value = "层级")
    @TableField("f_level")
    private Integer level;

    @ApiModelProperty(value = "是否底级")
    @TableField("f_is_leaf")
    private Boolean isLeaf;

    @ApiModelProperty(value = "父级ID")
    @TableField("f_parent_id")
    private Long parentId;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_is_enable")
    private Boolean isEnable;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;

    @ApiModelProperty(value = "下一级科目集合")
    private List<SubjectDTO> subjectDTOS;
}
