package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("une_cbill_item")
public class UneCbillItem {

    @TableId(value = "f_id")
    private long fId;

    private long fPid;

    private int fSortNo;

    /**
     * 项目编码
     */
    private String fItemCode;

    /**
     * 项目名称
     */
    private String fItemName;

    /**
     * 单位
     */
    private String fUnits;

    /**
     * 数量
     */
    private int fNumber;

    private BigDecimal fStd;

    /**
     * 金额
     */
    private BigDecimal fAmt;

    private int fVersion;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fCreateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fUpdateTime;

    private String fOperator;
}
