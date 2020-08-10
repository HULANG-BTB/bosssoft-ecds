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
 * 归档票据销毁记录
 * </p>
 *
 * @author liuke
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbr_stock_deptinvalid_voucher")
@ApiModel(value="FbrStockDeptinvalidVoucherPO对象", description="")
public class FbrStockDeptinvalidVoucherPO extends Model<FbrStockDeptinvalidVoucherPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_idcode")
    private String agenIdcode;

    @ApiModelProperty(value = "预警票号")
    @TableField("f_bill_no")
    private String billNo;

    @ApiModelProperty(value = "票据使用状态")
    @TableField("f_usage_status")
    private String usageStatus;

    @ApiModelProperty(value = "票据销毁业务单号")
    @TableField("f_destory_num")
    private String destoryNum;

    @ApiModelProperty(value = "票据销毁时间")
    @TableField("f_destory_time")
    private Date destoryTime;

    @ApiModelProperty(value = "票据销毁编制人")
    @TableField("f_destory_user")
    private String destoryUser;

    @ApiModelProperty(value = "审核人")
    @TableField("f_destory_author")
    private String destoryAuthor;

    @ApiModelProperty(value = "票据销毁备注信息")
    @TableField("f_destory_detail")
    private String destoryDetail;

    @ApiModelProperty(value = "票据销毁类型")
    @TableField("f_destory_type")
    private String destoryType;

    @ApiModelProperty(value = "归档状态")
    @TableField("f_is_save")
    private Boolean isSave;

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

    public static final String F_BILL_NO = "f_bill_no";

    public static final String F_USAGE_STATUS = "f_usage_status";

    public static final String F_DESTORY_NUM = "f_destory_num";

    public static final String F_DESTORY_TIME = "f_destory_time";

    public static final String F_DESTORY_USER = "f_destory_user";

    public static final String F_DESTORY_AUTHOR = "f_destory_author";

    public static final String F_DESTORY_DETAIL = "f_destory_detail";

    public static final String F_DESTORY_TYPE = "f_destory_type";

    public static final String F_IS_SAVE = "f_is_save";

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
