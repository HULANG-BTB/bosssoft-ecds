package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WriteOffReceiveVO {

    /**
     * 审验状态
     */
    private String state = "未审验";

    /**
     * 业务单号
     */
    private String no;

    /***
     * 编制日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    /**
     * 退票原因
     */
    private String reason;

    /**
     * 编制人
     */
    private String author;
}
