package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WriteOffReceiveVO {

    /**
     * 业务单号
     */
    private String fNo;

    /***
     * 编制日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
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
