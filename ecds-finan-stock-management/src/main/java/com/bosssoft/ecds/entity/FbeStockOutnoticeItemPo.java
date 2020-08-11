package com.bosssoft.ecds.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("fbe_stock_outnotice_item")
public class FbeStockOutnoticeItemPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 票据出库明细表_主键
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    /**
     * 出库表id
     */
    private Long fPid;

    /**
     * 序号
     */
    private Long fNo;

    /**
     * 票据代码（8位）
     */
    private String fBillCode;

    /**
     * 票据名称
     */
    private String fBillName;

    /**
     * 数量
     */
    private Integer fNumber;

    /**
     * 起始号
     */
    private String fBillNo1;

    /**
     * 终止号
     */
    private String fBillNo2;

    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime fUpdateTime;

    /**
     * 版本号
     */
    private Integer fVersion;

    /**
     * 逻辑删除（0未删1删除）
     */
    private Boolean fLogicDelete;


}
