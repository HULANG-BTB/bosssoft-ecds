package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author misheep
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_warehouse")
@ApiModel(value="WarehousePO对象", description="")
public class WarehousePo extends Model<WarehousePo> {


    @ApiModelProperty(value = "仓库表_主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "仓库ID")
    @TableField("f_warehouse_id")
    private Long warehouseId;

    @ApiModelProperty(value = "仓库name")
    @TableField("f_warehouse_name")
    private String warehouseName;

    @ApiModelProperty(value = "操作者id")
    @TableField("f_operator_id")
    private Long operatorId;

    @ApiModelProperty(value = "操作者name")
    @TableField("f_operator")
    private String operator;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Integer logicDelete;


    public static final String F_ID = "f_id";

    public static final String F_WAREHOUSE_ID = "f_warehouse_id";

    public static final String F_WAREHOUSE_NAME = "f_warehouse_name";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
