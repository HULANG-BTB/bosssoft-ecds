package com.bosssoft.ecds.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @author misheep
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "StockOutVo对象", description = "")
public class StockOutVo extends Model<StockOutVo> {

    @ApiModelProperty(value = "业务单号")
    private Long no;

    @ApiModelProperty(value = "区划编码")
    private String rgnCode;

    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 下列为主要项
     */
    @ApiModelProperty(value = "票据代码（8位）")
    private String billCode;

    @ApiModelProperty(value = "票据名称")
    private String billName;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "起始号")
    private String billNo1;

    @ApiModelProperty(value = "终止号")
    private String billNo2;


    @ApiModelProperty(value = "申请日期")
    private Date date;

    @ApiModelProperty(value = "领用人")
    private String useMan;

    @ApiModelProperty(value = "联系电话")
    private String linkTel;

    @ApiModelProperty(value = "联系地址")
    private String linkAddress;

    @ApiModelProperty(value = "编制人")
    private String author;

    @ApiModelProperty(value = "审核状态(0未1提交2通过3退回）")
    private Integer changeState;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "摘要")
    private String abstact;


    public static final String F_ID = "f_id";

    public static final String F_NO = "f_no";

    public static final String F_RGN_CODE = "f_rgn_code";

    public static final String F_WAREHOUSE_ID = "f_warehouse_id";

    public static final String F_DATE = "f_date";

    public static final String F_USE_MAN = "f_use_man";

    public static final String F_LINK_TEL = "f_link_tel";

    public static final String F_LINK_ADDRESS = "f_link_address";

    public static final String F_AUTHOR = "f_author";

    public static final String F_MEMO = "f_memo";

    public static final String F_VERSION = "f_version";

    public static final String F_ABSTACT = "f_abstact";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_CHANGE_STATE = "f_change_state";

    public static final String F_CHANGE_DATE = "f_change_date";

    public static final String F_CHANGE_MAN = "f_change_man";

    public static final String F_CHANGE_SITU = "f_change_situ";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

}
