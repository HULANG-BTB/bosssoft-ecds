package com.bosssoft.usm.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 根据编制日期查询退票信息
 * @author 朱文
 * create on 2020/8/11 16:33
 */
@Data
@ToString
public class DateVO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
