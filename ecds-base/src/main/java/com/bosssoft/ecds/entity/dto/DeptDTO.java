package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * @Author asus
 * @create 9/8/2020 下午4:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="FabDeptDTO对象", description="")
public class DeptDTO extends Model<DeptDTO> {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "区划编码")
    private String rgnId;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "是否启用")
    private Boolean isenable;

    @ApiModelProperty(value = "联系人")
    private String linkMan;

    @ApiModelProperty(value = "联系电话")
    private String linkTel;

    @ApiModelProperty(value = "联系电话")
    private String addr;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    private Long operatorId;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean logicDelete;

    @ApiModelProperty(value = "维护类型")
    private String alterCode;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "备注")
    private String note;

}
