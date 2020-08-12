package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbe_destory_DestroyConfirm")
public class DestroyConfirmPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销毁审验表id，主键
     */
    private Long id;

    /**
     * 票据ID
     */
    private String billId;

    /**
     * 票据编码
     */
    private String billCode;

    /**
     * 票据名称
     */
    private String billName;

    /**
     * 销毁单位id
     */
    private String agenIdCode;

    /**
     * 销毁单位名称
     */
    private String agenName;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 票据起始号
     */
    private String billNo1;

    /**
     * 票据结束号
     */
    private String billNo2;

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
     * 审核时间
     */
    private LocalDateTime DestroyConfirmDate;

    /**
     * 审核结果：0.审核未通过；1.审核通过
     */
    private Boolean result;

    /**
     * 审核未通过原因
     */
    private String reason;

    /**
     * 单号
     */
    private String destroyNo;

    /**
     * 审核人
     */
    private String DestroyConfirmMan;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 操作人
     */
    private String operator;


}
