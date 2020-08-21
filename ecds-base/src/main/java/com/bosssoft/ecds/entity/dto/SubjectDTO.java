package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "科目编码")
    private String code;

    @ApiModelProperty(value = "科目名称")
    private String name;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @Value("false")
    @ApiModelProperty(value = "是否底级,0否，1是")
    private Boolean leaf;

    @ApiModelProperty(value = "父级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @Value("true")
    @ApiModelProperty(value = "是否启用，0否，1是")
    private Boolean enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "下一级科目集合")
    private List<SubjectDTO> subjectDTOS;
}
