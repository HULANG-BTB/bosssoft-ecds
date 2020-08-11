package com.bosssoft.ecds.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class FabWarehousePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库表_主键
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    /**
     * 仓库ID
     */
    private Long fWarehouseId;

    /**
     * 仓库name
     */
    private String fWarehouseName;


}
