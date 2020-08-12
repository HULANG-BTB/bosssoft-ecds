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
    private Long id;

    /**
     * 父节点ID
     */
    private String pid;

    /**
     * 序号
     */
    private Integer sortNo;

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
     * 票据代码
     */
    private String billBatchCode;

    /**
     * 起始号
     */
    private String billNo1;

    /**
     * 终止号
     */
    private String billNo2;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 仓库ID
     */
    private String warehouseId;

    /**
     * 仓库名
     */
    private String warehouseName;


}
