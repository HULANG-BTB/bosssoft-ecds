package com.bosssoft.ecds.encodeserver.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import java.sql.Timestamp;

/**
 * @Author 黄杰峰
 * @Date 2020/8/5 0005 13:57
 * @Description 赋码表实体类
 */
@Data
@TableName("fab_create_code")
public class CodePo {
    /**
     * 主键
     */
    @TableId("f_id")
    private long fId;

    /**
     * 区划编码
     */
    private String fRegiId;

    /**
     * 分类号
     */
    private String fSortId;

    /**
     * 种类号
     */
    private String fTypeId;

    /**
     * 年度号
     */
    private String fAnnualId;

    /**
     * 终止编码
     */
    private long fEndCode;

    /**
     * 版本号
     */
    @Version
    private long fVersion;

    /**
     * 新码创建时间 - SQL中DATETIME类型对应java中Timestamp类型
     */
    private Timestamp fCreateTime;

    /**
     * 码更新时间（新赋码）
     */
    private Timestamp fUpdateTime;

    /**
     * 操作人名称
     */
    private String fOperator;

    /**
     * 操作人ID
     */
    private Long fOperatorId;
}
