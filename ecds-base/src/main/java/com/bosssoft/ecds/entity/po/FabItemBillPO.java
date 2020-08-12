package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
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
 * @author wuliming
 * @since 2020-08-06
 */
@Data
@TableName("fab_item_bill")
public class FabItemBillPO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "f_id", type = IdType.ASSIGN_ID)
    private Long fId;

    /**
     * 票据种类编码
     */
    @TableField("f_bill_code")
    private String fBillCode;

    /**
     * 项目编码
     */
    @TableField("f_item_id_code")
    private String fItemIdCode;

    /**
     * 是否启用
     */
    @TableField("f_is_enabled")
    private Boolean fIsEnabled;

    /**
     * 操作人id
     */
    @TableField(value = "f_oper_Id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    /**
     * 操作人名称
     */
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    /**
     * 创建时间
     */
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最后操作时间
     */
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 版本
     */
    @TableField("f_version")
    @Version
    private Integer version;


}
