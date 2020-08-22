package com.bosssoft.ecds.msg.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRecordQueryVo implements Serializable {
    /**
     * 查验记录表_主键
     */
    private Long id;

    /**
     * 查验类别
     */
    private String checkType;

    /**
     * 日期
     */
    private List<Date> period;

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
    /**
     * 每页大小
     */
    private Long limit;
    /**
     * 当前页码
     */
    private Long page;

}