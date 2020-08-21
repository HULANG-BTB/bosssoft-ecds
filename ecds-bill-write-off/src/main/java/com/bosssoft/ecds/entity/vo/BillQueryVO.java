package com.bosssoft.ecds.entity.vo;

import lombok.Data;

/**
 * @author hujierong
 * @date 2020-8-19
 */
@Data
public class BillQueryVO {
    /**
     * 单位识别码
     */
    private String agenIdCode;

    /**
     * 结束日期
     */
    private String end;
}
