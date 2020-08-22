package com.bosssoft.ecds.msg.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRecordVo implements Serializable {
    /**
     * 查验记录表_主键
     */
    private Long id;

    /**
     * 票据编码
     */
    private String billCode;

    /**
     * 查验类别
     */
    private String checkType;

    /**
     * 结果（0伪1真）
     */
    private Integer result;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 操作者（查验人）
     */
    private String operator;

}