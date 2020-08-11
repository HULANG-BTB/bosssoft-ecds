package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("fbr_voucher")
@ApiModel(value="VoucherPO对象", description="归档总览")
public class VoucherPO extends Model<VoucherPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_idcode")
    private String agenIdcode;

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
    private Long unauthorNumber;

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
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
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


    public static final String F_ID = "f_id";

    public static final String F_AGEN_IDCODE = "f_agen_idcode";

    public static final String F_AGEN_NAME = "f_agen_name";

    public static final String F_KIND_NAME = "f_kind_name";

    public static final String F_LINK_ADDR = "f_link_addr";

    public static final String F_APPLY_NUMBER = "f_apply_number";

    public static final String F_USE_NUMBER = "f_use_number";

    public static final String F_AUTHOR_NUMBER = "f_author_number";

    public static final String F_UNAUTHOR_NUMBER = "f_unauthor_number";

    public static final String F_WARN_NUMBER = "f_warn_number";

    public static final String F_RETURN_NUMBER = "f_return_number";

    public static final String F_DESTORY_NUMBER = "f_destory_number";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_VERSION = "f_version";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
