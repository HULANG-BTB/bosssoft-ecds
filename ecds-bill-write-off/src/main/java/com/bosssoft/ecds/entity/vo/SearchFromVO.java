package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
