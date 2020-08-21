package com.bosssoft.ecds.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

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
@ApiModel(value = "StockOutVo对象", description = "StockOutVo对象")
public class StockOutVo extends Model<StockOutVo> {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "区划编码")
    private String rgnCode;

    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    @ApiModelProperty(value = "领用人")
    private String useMan;

    @ApiModelProperty(value = "申请日期")
    private Date date;

    @ApiModelProperty(value = "编制人")
    private String author;

    @ApiModelProperty(value = "审核状态(0新建1保存2提交3通过4退回）")
    private Integer changeState;

    @ApiModelProperty(value = "摘要")
    private String abstact;

    /**
     * altercode:变更状态，1新增　2修改   3 删除
     * 用于出库变动表记录
     */
    private Integer altercode;

    /**
     * 出库所含的出库明细
     */
    private List<StockOutItemVo> outItemVos;

}
