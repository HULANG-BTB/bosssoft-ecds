package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hujierong
 * @date 2020-8-14
 */
@Data
public class WriteOffApplyVO {
    /**
     * 业务单号
     */
    private String fNo;

    /**
     * 编制人
     */
    private String fAuthor;

    /**
     * 编制日期
     */
    private Date fDate;

    /**
     * 合计份数
     */
    private Integer fNumber;

    /**
     * 总金额
     */
    private BigDecimal fTotalAmt;

    /**
     * 备注
     */
    private String fMemo;

    /**
     * 状态： 1 未审验 2 已审验
     */
    private Boolean fChangeState;

    /**
     * 审验结果：1 良好 2 合格 3 问题 4 整改通过
     */
    private Boolean fCheckResult;
}
