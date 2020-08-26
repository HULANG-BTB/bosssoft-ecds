package com.bosssoft.ecds.domain.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 出库票据的后端PO类
 * @author 12964
 * @return
 * @date 2020/8/23 15:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "fbe_stock_outnotice_item")
@ApiModel(description = "出库的po对象，与数据库字段对应")
public class FbeStockOutPO implements Serializable {
    /**
     * 票据出库明细表_主键
     */
    @ApiModelProperty(value = "票据出库明细表_主键")
    private Long fId;

    /**
     * 出库表id
     */
    @ApiModelProperty(value = "出库表id")
    private Long fPid;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long fNo;

    /**
     * 票据代码（8位）
     */
    @ApiModelProperty(value = "票据代码")
    private String fBillPrecode;

    /**
     * 票据名称
     */
    @ApiModelProperty(value = "票据名称")
    private String fBillName;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer fNumber;

    /**
     * 起始号
     */
    @ApiModelProperty(value = "起始号")
    private String fBillNo1;

    /**
     * 终止号
     */
    @ApiModelProperty(value = "终止号")
    private String fBillNo2;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date fCreateTime;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private Date fUpdateTime;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer fVersion;

    /**
     * 逻辑删除（0未删1删除）
     */
    @ApiModelProperty(value = "逻辑删除")
    private Boolean fLogicDelete;
}
