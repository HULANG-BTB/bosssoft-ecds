package com.bosssoft.ecds.entity.dto;

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
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ReceiveFinanceapplyDto", description = "ReceiveFinanceapplyDto")
public class ReceiveFinanceapplyDto extends Model<ReceiveFinanceapplyDto> {

    @ApiModelProperty(value = "票据代码（8位）")
    private String billPrecode;

    @ApiModelProperty(value = "数量")
    private Integer number;

    /**
     * 单位和票据池申请时为空
     */
    @ApiModelProperty(value = "起始号")
    private String billNo1;

    /**
     * 单位和票据池申请时为空
     */
    @ApiModelProperty(value = "终止号")
    private String billNo2;

    /**
     * 票据池领用人默认为"票据池"
     */
    @ApiModelProperty(value = "领用人")
    private String useMan;
    /**
     * 票据池申请时为空
     */
    @ApiModelProperty(value = "编制人")
    private String author;

}
