package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fbe_writeoff")
@ApiModel(value="WriteOffPO对象")
public class WriteOffPO {

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

    @ApiModelProperty(value = "审验日期")
    @TableField("f_check_date")
    private Date fCheckDate;

    @ApiModelProperty(value = "编制人")
    @TableField("f_author")
    private String fAuthor;

    @ApiModelProperty(value = "编制日期")
    @TableField("f_date")
    private Date fDate;

    @ApiModelProperty(value = "审验结果：1 良好 2 合格 3 问题 4 整改通过")
    @TableField("f_check_result")
    private Short fCheckResult;

    @ApiModelProperty(value = "备注")
    @TableField("f_memo")
    private String fMemo;

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

    @ApiModelProperty(value = "状态：1 未审验，2 已审验")
    @TableField("f_change_state")
    private short fChangeState;

    @ApiModelProperty(value = "审验申请单业务唯一号")
    @TableField("f_apply_id")
    private String fApplyId;

    @ApiModelProperty(value = "接收数据批次号")
    @TableField("f_batch_no")
    private Integer fBatchNo;

    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long fOperatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String fOperator;

}
