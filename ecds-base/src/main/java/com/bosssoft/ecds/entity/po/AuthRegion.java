package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: lpb
 * @create: 2020-08-05 10:34
 */
@Data
@NoArgsConstructor
@TableName("afa_auth_region")
public class AuthRegion {

    @TableId(value = "f_id",type = IdType.AUTO)
    private Long id;

    @TableField("f_code")
    private String code;

    @TableField("f_name")
    private String name;

    @TableField("f_mnem")
    private String mnem;

    @TableField("f_parentid")
    private Long parentId;

    @TableField("f_level")
    private Integer level;

    @TableField("f_is_assortment")
    private boolean assortment;

    @TableField("f_is_leaf")
    private boolean leaf;

    @TableField("f_is_enabled")
    private boolean enabled;

    @TableField("f_is_toprgn")
    private boolean topRegion;

    @TableField("f_busiparent_id")
    private Long busParentId;

    @TableField("f_org_name")
    private String orgName;

    @TableField("f_remark")
    private String remark;

    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "f_update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField("f_version")
    @Version
    private Integer version;

}
