package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbr_archive")
@ApiModel(value="VoucherPO对象", description="归档总览")
public class ArchivePO extends Model<ArchivePO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("f_agen_name")
    private String agenName;

    @ApiModelProperty(value = "单位分类")
    @TableField("f_kind_name")
    private String kindName;

    @ApiModelProperty(value = "单位地址")
    @TableField("f_link_addr")
    private String linkAddr;

    @ApiModelProperty(value = "票据领用数量")
    @TableField("f_apply_number")
    private Long applyNumber;

    @ApiModelProperty(value = "票据使用数量")
    @TableField("f_use_number")
    private Long useNumber;

    @ApiModelProperty(value = "票据已审核数量")
    @TableField("f_author_number")
    private Long authorNumber;

    @ApiModelProperty(value = "票据未审核数量")
    @TableField("f_unauthor_number")
    private Long unAuthorNumber;

    @ApiModelProperty(value = "票据预警票数")
    @TableField("f_warn_number")
    private Long warnNumber;

    @ApiModelProperty(value = "票据退票数量")
    @TableField("f_return_number")
    private Long returnNumber;

    @ApiModelProperty(value = "票据销毁数量")
    @TableField("f_destory_number")
    private Long destoryNumber;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
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

    @ApiModelProperty(value = "删除状态")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
