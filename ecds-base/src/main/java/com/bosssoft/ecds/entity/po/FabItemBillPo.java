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
public class FabItemBillPo implements Serializable {

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
    @TableField("f_item_code")
    private String fItemIdCode;

    /**
     * 是否启用
     */
    @TableField("f_is_enabled")
    private Boolean fIsEnabled;

    /**
     * 操作人id
     */
    @TableField("f_oper_Id")
    private String operatorId;

    /**
     * 操作人名称
     */
    @TableField("f_operator")
    private String operator;

    /**
     * 创建时间
     */
    @TableField("f_create_date")
    private Date createTime;

    /**
     * 最后操作时间
     */
    @TableField("f_update_date")
    private Date updateTime;

    /**
     * 版本
     */
    @TableField("f_version")
    @Version
    private Integer version;


}
