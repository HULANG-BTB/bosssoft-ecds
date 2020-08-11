package com.bosssoft.ecds.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author misheep
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbe_stock_outnotice")
public class FbeStockOutnoticePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    /**
     * 业务单号
     */
    private Long fNo;

    /**
     * 区划编码
     */
    private String fRgnCode;

    /**
     * 仓库ID
     */
    private Long fWarehouseId;

    /**
     * 申请日期
     */
    private LocalDateTime fDate;

    /**
     * 领用人
     */
    private String fUseMan;

    /**
     * 联系电话
     */
    private String fLinkTel;

    /**
     * 联系地址
     */
    private String fLinkAddress;

    /**
     * 编制人
     */
    private String fAuthor;

    /**
     * 备注
     */
    private String fMemo;

    /**
     * 版本号
     */
    private Boolean fVersion;

    /**
     * 摘要
     */
    private String fAbstact;

    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime fUpdateTime;

    /**
     * 逻辑删除(0未删除1删除)
     */
    private Boolean fLogicDelete;


}
