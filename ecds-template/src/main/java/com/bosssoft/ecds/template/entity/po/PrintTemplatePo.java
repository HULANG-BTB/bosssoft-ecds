package com.bosssoft.ecds.template.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 打印模板表
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-17
 */
@Data
@TableName("uaa_print_template")
public class PrintTemplatePo extends Model<PrintTemplatePo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    /**
     * 区划编码
     */
    @TableField("f_rgn_code")
    private String rgnCode;

    /**
     * 种类编码
     */
    @TableField("f_sort_id")
    private String sortId;

    /**
     * 分类编码
     */
    @TableField("f_type_id")
    private String typeId;

    /**
     * 单位识别码
     */
    @TableField("f_agen_id_code")
    private String agenIdCode;

    /**
     * 开票点ID
     */
    @TableField("f_place_id")
    private String placeId;

    /**
     * 模板名称
     */
    @TableField("f_name")
    private String name;

    /**
     * 模板文本
     */
    @TableField("f_template")
    private String template;

    /**
     * 备注
     */
    @TableField("f_memo")
    private String memo;

    /**
     * 优先级，默认0。1为默认模板
     */

    @TableField("f_priority")
    private String priority;

    /**
     * 乐观锁
     */
    @Version
    @TableField("f_version")
    private Long version;

    /**
     * 操作人
     */
    @TableField("f_operator")
    private String operator;

    /**
     * 操作人ID
     */
    @TableField("f_operator_id")
    private Long operatorId;

    /**
     * 创建时间
     */
    @TableField("f_create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("f_update_time")
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
