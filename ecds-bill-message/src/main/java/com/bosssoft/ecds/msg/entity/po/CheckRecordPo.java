package com.bosssoft.ecds.msg.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "fbe_check_record")
public class CheckRecordPo {
    /**
     * 查验记录表_主键
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    /**
     * 票据编码
     */
    @TableField(value = "f_bill_code")
    private String billCode;

    /**
     * 查验类别
     */
    @TableField(value = "f_check_type")
    private String checkType;

    /**
     * 结果（0伪1真）
     */
    @TableField(value = "f_result")
    private Integer result;

    /**
     * 创建日期
     */
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新日期
     */
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 操作者（查验人）
     */
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;
    /**
     * 版本号
     */
    @Version
    @TableField(value = "f_version", fill = FieldFill.INSERT)
    private Integer version;


    /**
     * 逻辑删除（0未删1删除）
     */
    @TableField(value = "f_logic_delete", fill = FieldFill.INSERT)
    private Boolean logicDelete;

    public static final String COL_F_ID = "f_id";

    public static final String COL_F_CHECK_ID = "f_check_id";

    public static final String COL_F_VERSION = "f_version";

    public static final String COL_F_CHECK_TYPE = "f_check_type";

    public static final String COL_F_CREATE_TIME = "f_create_time";

    public static final String COL_F_UPDATE_TIME = "f_update_time";

    public static final String COL_F_OPERATOR = "f_operator";

    public static final String COL_F_BILL_CODE = "f_bill_code";

    public static final String COL_F_RESULT = "f_result";

    public static final String COL_F_LOGIC_DELETE = "f_logic_delete";
}