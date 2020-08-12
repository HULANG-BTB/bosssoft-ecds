package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
@Data
//flase 不调用父类的属性
@EqualsAndHashCode(callSuper = false)
//控制getter和setter方法形式，set方法返回的是对象名称，更加的直观，适合对象赋值时的连续赋值参数
@Accessors(chain = true)
@TableName("ube_destroy_apply")
public class ApplyPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 单号
     */
    private String destroyNo;

    /**
     * 区划编码
     */
    private String rgnCode;

    /**
     * 单位识别码
     */
    private String agenIdCode;

    /**
     * 申请人
     */
    private String applyAuthor;

    /**
     * 备注
     */
    private String destroyMemo;

    /**
     * 编制日期
     */
    private LocalDateTime date;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     * 申请日期
     */
    private LocalDateTime applyDate;

    /**
     * 申请人
     */
    private String applyMan;

    /**
     * 销毁类型：0.核销票据销毁；1.库存票据销毁
     */
    private Boolean destroyType;


}
