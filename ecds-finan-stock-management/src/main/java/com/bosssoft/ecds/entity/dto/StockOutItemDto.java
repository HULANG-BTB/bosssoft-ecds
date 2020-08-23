package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author misheep
 * @since 2020-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockOutnoticeItemVo对象", description="")
public class StockOutItemDto extends Model<StockOutItemDto> {

    @ApiModelProperty(value = "票据出库明细表_主键")
    private Long id;

    @ApiModelProperty(value = "出库表id")
    private Long pid;

    @ApiModelProperty(value = "票据代码（8位）")
    private String billPrecode;

    @ApiModelProperty(value = "票据名称")
    private String billName;

    @ApiModelProperty(value = "数量")
    private Integer number;

    /**
     * 最大数量
     */
    private Integer maxNum;

    @ApiModelProperty(value = "起始号")
    private String billNo1;

    @ApiModelProperty(value = "终止号")
    private String billNo2;
}
