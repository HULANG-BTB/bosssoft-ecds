package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lin.wanning
 * @since 2020-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FabFinDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "f_id")
    private Long id;

    /**
     * 区划code
     */
    @TableField("f_rgn_code")
    private String rgnCode;

    /**
     * 编码
     */
    @TableField("f_findept_code")
    private String findeptCode;

    /**
     * 名称
     */
    @TableField("f_findept_name")
    private String findeptName;

    /**
     * 是否启用
     */
    @TableField("f_is_enable")
    private Boolean isEnable;

    /**
     * 经办人
     */
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    /**
     * 经办人
     */
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;


    /**
     * 创建时间
     */

    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 联系人
     */

    @TableField("f_linkman")
    private String linkman;

    /**
     * 电话
     */
    @TableField("f_link_tel")
    private String linkTel;

    /**
     * 地址
     */
    @TableField("f_addr")
    private String addr;

    /**
     * 版本号
     */
    @Version
    @TableField("f_version")
    private Integer version;

    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 自定义1
     */
    @TableField("f_custom1")
    private String custom1;

    /**
     * 自定义2
     */
    @TableField("f_custom2")
    private String custom2;


}
