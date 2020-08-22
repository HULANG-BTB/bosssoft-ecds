package com.bosssoft.ecds.entity.dto;

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
 * @author AloneH
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PayerPO对象", description="")
public class PayerDTO extends Model<PayerDTO> {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "交款人ID")
    private Long payerId;

    @ApiModelProperty(value = "交款人名称")
    private String payerName;

    @ApiModelProperty(value = "助记码")
    private String mnen;

    @ApiModelProperty(value = "户名")
    private String payerUsername;

    @ApiModelProperty(value = "是否欠缴")
    private Boolean isunpaid;

    @ApiModelProperty(value = "划缴协议号")
    private String payAgreeno;

    @ApiModelProperty(value = "缴款人类型")
    private String payerType;

    @ApiModelProperty(value = "缴款人证件号")
    private String payerCertno;

    @ApiModelProperty(value = "所属银行")
    private String payerBank;

    @ApiModelProperty(value = "开户行名称")
    private String payerBankname;

    @ApiModelProperty(value = "账号")
    private String payerAcctname;

    @ApiModelProperty(value = "电话")
    private String tel;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "区划ID")
    private String rgncode;

    @ApiModelProperty(value = "单位识别码")
    private String agenIdcode;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    private Long operatorId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean logicDelete;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "备注")
    private String note;


    public static final String F_ID = "f_id";

    public static final String F_PAYER_ID = "f_payer_id";

    public static final String F_PAYER_NAME = "f_payer_name";

    public static final String F_MNEN = "f_mnen";

    public static final String F_PAYER_USERNAME = "f_payer_username";

    public static final String F_ISUNPAID = "f_isunpaid";

    public static final String F_PAY_AGREENO = "f_pay_agreeno";

    public static final String F_PAYER_TYPE = "f_payer_type";

    public static final String F_PAYER_CERTNO = "f_payer_certno";

    public static final String F_PAYER_BANK = "f_payer_bank";

    public static final String F_PAYER_BANKNAME = "f_payer_bankname";

    public static final String F_PAYER_ACCTNAME = "f_payer_acctname";

    public static final String F_TEL = "f_tel";

    public static final String F_EMAIL = "f_email";

    public static final String F_RGNCODE = "f_rgncode";

    public static final String F_AGEN_IDCODE = "f_agen_idcode";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    public static final String F_VERSION = "f_version";

    public static final String F_NOTE = "f_note";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
