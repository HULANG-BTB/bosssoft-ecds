package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
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
//@EqualsAndHashCode(callSuper = false)
//控制getter和setter方法形式，set方法返回的是对象名称，更加的直观，适合对象赋值时的连续赋值参数
//@Accessors(chain = true)
@TableName("ube_destroy_apply")
public class ApplyPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("f_id")
    private Long fId;

    /**
     * 单号
     */
    private String fDestroyNo;

    /**
     * 区划编码
     */
    private String fRgnCode;

    /**
     * 单位识别码
     */
    private String fAgenIdCode;

    /**
     * 申请人
     */
    private String fApplyAuthor;

    /**
     * 备注
     */
    private String fDestroyMemo;

    /**
     * 编制日期
     */
    private LocalDateTime fDate;

    /**
     * 版本号
     */
    private Integer fVersion;

    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 修改时间
     */
    private LocalDateTime fUpdateTime;

    /**
     * 操作人id
     */
    private Long fOperatorId;

    /**
     * 操作人
     */
    private String fOperatorName;

    /**
     * 申请日期
     */
    private LocalDateTime fApplyDate;

    /**
     * 申请人
     */
    private String fApplyMan;

    /**
     * 销毁类型：0.核销票据销毁；1.库存票据销毁
     */
    private Boolean fDestroyType;

    /**
     * 审核状态：0.未审核；1.已审核
     */
    private Boolean fStatus;
}
