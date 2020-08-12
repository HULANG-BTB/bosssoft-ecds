package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;
import java.util.List;

/**
 * @author wy
 * @create 2020-08-10 20:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SubjectDTO对象", description="")
public class SubjectDTO extends Model<SubjectDTO> {

    @ApiModelProperty(value = "科目id")
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "科目编码")
    private String code;

    @ApiModelProperty(value = "科目名称")
    private String name;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "是否底级")
    private Boolean leaf;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "操作人id")
    private Long operatorId;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean logicDelete;

    @ApiModelProperty(value = "下一级科目集合")
    private List<SubjectDTO> subjectDTOS;
}
