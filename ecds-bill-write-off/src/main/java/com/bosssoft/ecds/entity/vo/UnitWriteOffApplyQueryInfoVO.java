package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author hujierong
 * @date 2020-8-14
 */
@Data
public class UnitWriteOffApplyQueryInfoVO {
    /**
     * 单位识别码
     */
    private String agenIdCode;

    /**
     * 业务单号
     */
    private String no;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 每页的数据数量
     */
    private int pageSize;
}
