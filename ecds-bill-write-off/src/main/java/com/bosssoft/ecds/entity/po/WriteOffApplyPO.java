package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hjr
 */
@Data
@TableName("ube_writeoff_apply")
@ApiModel(value="WriteoffApplyPO对象")
public class WriteOffApplyPO {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    @ApiModelProperty(value = "区划编码")
    @TableField("f_rgn_code")
    private String fRgnCode;

    @ApiModelProperty(value = "业务单号")
    @TableField("f_no")
    private String fNo;

    @ApiModelProperty(value = "单位识别码")
    @TableField("f_agen_id_code")
    private String fAgenIdCode;

    @ApiModelProperty(value = "编制人")
    @TableField("f_author")
    private String fAuthor;

    @ApiModelProperty(value = "编制日期")
    @TableField("f_date")
    private Date fDate;

    @ApiModelProperty(value = "合计份数")
    @TableField("f_number")
    private int fNumber;

    @ApiModelProperty(value = "总金额")
    @TableField("f_total_amt")
    private BigDecimal fTotalAmt;

    @ApiModelProperty(value = "备注")
    @TableField("f_memo")
    private String fMemo;

    @ApiModelProperty(value = "状态： 1 未审验 2 已审验")
    @TableField("f_change_state")
    private Boolean fChangeState;

    @ApiModelProperty(value = "审验结果：1 良好 2 合格 3 问题 4 整改通过")
    @TableField("f_check_result")
    private Boolean fCheckResult;

    @ApiModelProperty(value = "是否上报：1 未上报 2 已上报")
    @TableField("f_is_upload")
    private Boolean fIsUpload;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer fVersion;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date fCreateTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date fUpdateTime;

    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long fOperatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String fOperator;
}
