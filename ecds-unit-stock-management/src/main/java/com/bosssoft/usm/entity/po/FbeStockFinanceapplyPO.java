package com.bosssoft.usm.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * fbe_stock_financeapply
 * 
 * @author bianj
 * @version 1.0.0 2020-08-10
 */
@Data
@TableName("fbe_stock_financeapply")
public class FbeStockFinanceapplyPO {

    /** 申领表_主键 */
    @TableId(type = IdType.AUTO)
    private Long fId;

    /** 单号 */
    @TableField
    private Long fNo;

    /** 区域编码 */
    @TableField
    private String fRgnCode;

    /** 单位编码 */
    @TableField
    private Long fCode;

    /** 单位名称 */
    @TableField
    private String fName;

    /** 单位类型 */
    @TableField
    private String fKindName;

    /** 票据代码 */
    @TableField
    private String fBillCode;

    /** 批次数量 */
    @TableField
    private Integer fBatchNum;

    /** 联系人 */
    @TableField
    private String fLinkMan;

    /** 联系方式 */
    @TableField
    private String fLinkTel;

    /** 联系地址 */
    @TableField
    private String fLinkAddr;

    /** 编制人 */
    @TableField
    private String fAuthor;

    /** 备注 */
    @TableField
    private String fMemo;

    /** 版本号 */
    @TableField
    private Integer fVersion;

    /** 摘要 */
    @TableField
    private String fAbstract;

    /** 创建日期 */
    @TableField
    private Date fCreateTime;

    /** 更新日期 */
    @TableField
    private Date fUpdateTime;

    /** 审核日期 */
    @TableField
    private Date fChangeDate;

    /** 审核人 */
    @TableField
    private String fChangeMan;

    /** 审核意见 */
    @TableField
    private String fChangeSitu;

    /** 状态（入库与否） */
    @TableField
    private Integer fStatus;

    /** 逻辑删除（0未删1删除） */
    @TableField
    private Boolean fLogicDelete;

}