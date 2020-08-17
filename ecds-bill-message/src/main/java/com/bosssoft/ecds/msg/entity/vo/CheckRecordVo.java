package com.bosssoft.ecds.msg.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRecordVo {
    /**
     * 查验记录表_主键
     */
    private Long id;



    /**
     * 查验类别
     */
    private String checkType;

    /**
     * 创建日期
     */
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