package com.bosssoft.ecds.entity.vo;

import lombok.Data;

@Data
public class SearchFromVO {

    /** 业务单号 */
    private String number;

    /** 起始日期 */
    private String date1;

    /** 结束日期 */
    private String date2;

    /** 状态 */
    private String state;

}
