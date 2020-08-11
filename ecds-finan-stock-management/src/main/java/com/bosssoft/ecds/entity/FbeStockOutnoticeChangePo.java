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
@TableName("fbe_stock_outnotice_change")
public class FbeStockOutnoticeChangePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    /**
     * 业务ID
     */
    private Long fBussId;

    /**
     * 变更状态
     */
    private Integer fChangeState;

    /**
     * 变更日期
     */
    private LocalDateTime fChangeDate;

    /**
     * 变更人
     */
    private String fChangeMan;

    /**
     * 变更情况
     */
    private String fChangeSitu;

    /**
     * 版本号
     */
    private Integer fVersion;

    /**
     * 1新增　2修改   3 删除
     */
    private Integer fAltercode;

    /**
     * 创建日期
     */
    private LocalDateTime fCreateTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime fUpdateTime;


}
