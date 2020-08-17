package com.bosssoft.ecds.msg.entity.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRecordDto {
    /**
     * 查验记录表_主键
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 查验类别
     */
    private String checkType;

    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 操作者（查验人）
     */
    private String operator;

    /**
     * 票据编码
     */
    private String billCode;

    /**
     * 结果（0伪1真）
     */
    private Integer result;

}