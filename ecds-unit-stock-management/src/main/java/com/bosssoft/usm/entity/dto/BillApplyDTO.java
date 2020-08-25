package com.bosssoft.usm.entity.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @author 张东海
 * @date 2020/8/12
 * @description
 */
@Data
public class BillApplyDTO {
    private BigInteger agenIdCode;
    private String agenName;
    private String kindName;
    private String linkAddr;
    private String linkMan;
    private LocalDateTime createTime;
    private LocalDateTime changeDate;
    private String changeSitu;
    private String changeMan;
    private Integer status;
}
