package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * @author ZhuWen
 * @since 2020-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ube_stock_return_item")
@ApiModel(value="StockReturnItemPO对象", description="")
public class StockReturnItemPO extends Model<StockReturnItemPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父级ID")
    @TableField("f_pid")
    private Long pid;

    @ApiModelProperty(value = "序号")
    @TableField("f_no")
    private Long no;

    @ApiModelProperty(value = "票据代码")
    @TableField("f_bill_code")
    private String billCode;

    @ApiModelProperty(value = "数量")
    @TableField("f_number")
    private Integer number;

    @ApiModelProperty(value = "起始票号")
    @TableField("f_bill_no1")
    private String billNo1;

    @ApiModelProperty(value = "终止票号")
    @TableField("f_bill_no2")
    private String billNo2;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    public static final String F_ID = "f_id";

    public static final String F_PID = "f_pid";

    public static final String F_NO = "f_no";

    public static final String F_BILL_CODE = "f_bill_code";

    public static final String F_NUMBER = "f_number";

    public static final String F_BILL_NO1 = "f_bill_no1";

    public static final String F_BILL_NO2 = "f_bill_no2";

    public static final String F_VERSION = "f_version";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
