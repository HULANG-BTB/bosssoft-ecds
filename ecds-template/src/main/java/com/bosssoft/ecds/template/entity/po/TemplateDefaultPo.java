package com.bosssoft.ecds.template.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 默认模板表，保存默认的模板编号
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-22
 */
@Data
@TableName("uba_template_default")
public class TemplateDefaultPo extends Model<TemplateDefaultPo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    /**
     * 6位的票据代码（前6位，没有年度编码）
     */
    @TableField("f_billcode")
    private String billcode;

    /**
     * 单位识别码
     */
    @TableField("f_agen_id_code")
    private String agenIdCode;

    /**
     * 默认模板的类型。打印模板是"print"
     */
    @TableField("f_type")
    private String type;

    /**
     * 储存模板表的主键，比如打印模板的id
     */
    @TableField("f_default_id")
    private Long defaultId;

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

}
