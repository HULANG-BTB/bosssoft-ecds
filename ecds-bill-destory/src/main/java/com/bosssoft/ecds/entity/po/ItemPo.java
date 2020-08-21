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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ube_destroy_apply_item")
public class ItemPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long fId;

    /**
     * 父节点ID
     */
    private String fPid;

    /**
     * 序号
     */
    private Integer fSortNo;

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
     * 票据代码
     */
    private String fBillBatchCode;

    /**
     * 起始号
     */
    private String fBillNo1;

    /**
     * 终止号
     */
    private String fBillNo2;

    /**
     * 数量
     */
    private Integer fNumber;

    /**
     * 仓库ID
     */
    private String fWarehouseId;

    /**
     * 仓库名
     */
    private String fWarehouseName;


}
