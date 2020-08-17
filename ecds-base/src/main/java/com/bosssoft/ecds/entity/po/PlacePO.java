package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("uab_place")
@ApiModel(value="PlacePO对象", description="")
public class PlacePO extends Model<PlacePO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区划ID")
    @TableField("f_rgn_id")
    private String rgnId;

    @ApiModelProperty(value = "开票点编码")
    @TableField("f_place_id")
    private String placeId;

    @ApiModelProperty(value = "开票点名称")
    @TableField("f_place_name")
    private String placeName;

    @ApiModelProperty(value = "上级单位编码")
    @TableField("f_agen_id")
    private String agenId;

    @ApiModelProperty(value = "助记码")
    @TableField("f_mmen")
    private String mmen;

    @ApiModelProperty(value = "级次")
    @TableField("f_level")
    private Integer level;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_isenable")
    private Boolean isenable;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;


    public static final String F_ID = "f_id";

    public static final String F_RGN_ID = "f_rgn_id";

    public static final String F_PLACE_ID = "f_place_id";

    public static final String F_PLACE_NAME = "f_place_name";

    public static final String F_AGEN_ID = "f_agen_id";

    public static final String F_MMEN = "f_mmen";

    public static final String F_LEVEL = "f_level";

    public static final String F_ISENABLE = "f_isenable";

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
