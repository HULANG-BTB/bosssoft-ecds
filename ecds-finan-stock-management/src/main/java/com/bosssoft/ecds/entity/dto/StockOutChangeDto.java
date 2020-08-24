package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author misheep
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockOutChangeDto对象", description="StockOutChangeDto对象")
public class StockOutChangeDto extends Model<StockOutChangeDto> {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "业务ID")
    private Long pid;

    @ApiModelProperty(value = "审核状态(0新建1保存2提交3通过4退回）")
    private Integer changeState;

    @ApiModelProperty(value = "变更日期")
    private Date changeDate;

    @ApiModelProperty(value = "变更情况")
    private String changeSitu;

    @ApiModelProperty(value = "变更状态(0无用1新增2修改3删除)")
    private Integer altercode;

    @ApiModelProperty(value = "操作者id")
    private Long operatorId;

    @ApiModelProperty(value = "操作者name")
    private String operator;
}
