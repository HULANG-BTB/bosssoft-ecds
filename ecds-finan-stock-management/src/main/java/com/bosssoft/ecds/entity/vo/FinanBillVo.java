package com.bosssoft.ecds.entity.vo;

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
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FinanBillVo对象", description = "")
public class FinanBillVo extends Model<FinanBillVo> {

    @ApiModelProperty(value = "票据编码（18位）")
    private String billCode;

    @ApiModelProperty(value = "票据代码（8位）")
    private String billPrecode;

    @ApiModelProperty(value = "票据ID（10）")
    private String billId;

    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    @ApiModelProperty(value = "票据名称")
    private String billName;
//
//    @ApiModelProperty(value = "单位编码")
//    private Long agenCode;
//
//    @ApiModelProperty(value = "单位名称")
//    private String agenName;
//
//    @ApiModelProperty(value = "生效日期")
//    private Date effDate;
//
//    @ApiModelProperty(value = "失效日期")
//    private Date expDate;

    @ApiModelProperty(value = "经办人ID")
    private Long operId;

    @ApiModelProperty(value = "经办人")
    private String operator;

//    @ApiModelProperty(value = "经办日期")
//    private Date opeDate;

//    @ApiModelProperty(value = "是否发放")
//    private Integer isPutout;
//
//    @ApiModelProperty(value = "是否退票")
//    private Integer isReturn;
//
//    @ApiModelProperty(value = "是否作废")
//    private Integer isInvalid;
//
//    @ApiModelProperty(value = "创建时间")
//    private Date createTime;
//
//    @ApiModelProperty(value = "最后修改时间")
//    private Date updateTime;

    @ApiModelProperty(value = "区划编码")
    private String rgnCode;

//    @ApiModelProperty(value = "核销状态（0未核销1需要核销2不需核销）")
//    private Integer hxStatus;

}
