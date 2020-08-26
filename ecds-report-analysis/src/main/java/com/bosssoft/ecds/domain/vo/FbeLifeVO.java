package com.bosssoft.ecds.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zouyou
 * @version 1.0
 * @description 生命周期的前端展示VO
 * @date 2020/8/24 18:28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FbeLifeVO {
    /**
     * 票据编码（18位）
     */
    private String fBillCode;

    /**
     * 票据名称
     */
    private String fBillName;

    /**
     * 入库审核人
     */
    private String fStockInAuthor;

    /**
     * 入库创建时间
     */
    private Date fStockInCreateTime;

    /**
     * 入库的票据来源
     */
    private String fStockInBillSource;

    /**
     * 入库仓库ID
     */
    private Long fStockInWarehouseId;

    /**
     * 入库审核状态（0未审核1通过2退回）
     */
    private Integer fStockInChangeStatus;

    /**
     * 出库审核人
     */
    private String fStockOutAuthor;

    /**
     * 出库创建时间
     */
    private Date fStockOutCreateTime;

    /**
     * 出库票据来源
     */
    private String fStockOutBillSource;

    /**
     * 出库票据仓库ID
     */
    private Long fStockOutWarehouseId;

    /**
     * 出库票据审核状态（0未审核1通过2退回）
     */
    private Integer fStockOutChangeStatus;

    /**
     * 开票票据票面金额
     */
    private BigDecimal fWriteoffAmt;

    /**
     * 开票操作人
     */
    private String fWriteoffOperator;

    /**
     * 开票票据创建时间
     */
    private Date fWriteoffCreateTime;

    /**
     * 开票单位名称
     */
    private String fWriteoffUnitName;

    /**
     * 销毁单位名称
     */
    private String fDestroryUnitName;

    /**
     * 销毁票据申请人
     */
    private String fDestroryApplyMan;

    /**
     * 销毁类型：0.核销票据销毁；1.库存票据销毁
     */
    private Boolean fDestroyType;

    /**
     * 审核状态：0.未审核；1.已审核但未通过；2.已审核并通过
     */
    private Integer fDestroryStatus;

    /**
     * 销毁票据创建时间
     */
    private Date fDestroryCreateTime;
}
