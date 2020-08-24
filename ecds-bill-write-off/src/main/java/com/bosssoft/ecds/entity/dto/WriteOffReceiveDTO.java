package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WriteOffReceiveDTO {

    /**
     * 业务单号
     */
    private String fNo;

    /***
     * 编制日期
     */
    private Date fDate;

    /**
     * 退票原因
     */
    private String fMemo;

    /**
     * 编制人
     */
    private String fAuthor;
}
