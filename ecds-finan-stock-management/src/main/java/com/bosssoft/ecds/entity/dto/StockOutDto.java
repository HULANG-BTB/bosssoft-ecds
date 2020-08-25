package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bosssoft.ecds.entity.vo.StockOutItemVo;
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
@ApiModel(value="StockOutDto对象", description="")
public class StockOutDto extends Model<StockOutDto> {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "业务单号")
    private Long no;

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

}
