package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lin.wanning
 * @since 2020-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FabFinDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "f_id")
    private Long fId;

    /**
     * 区划code
     */
    private String fRgnCode;

    /**
     * 编码
     */
    private String fFindeptCode;

    /**
     * 名称
     */
    private String fFindeptName;

    /**
     * 是否启用
     */
    private Boolean fIsEnable;

    /**
     * 经办人
     */
    private String fOperator;


    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 联系人
     */
    private String fLinkman;

    /**
     * 电话
     */
    private String fLinkTel;

    /**
     * 地址
     */
    private String fAddr;

    /**
     * 版本号
     */
    @Version
    private Integer fVersion;

    private LocalDateTime fUpdateTime;

    /**
     * 自定义1
     */
    private String fCustom1;

    /**
     * 自定义2
     */
    private String fCustom2;


}
