package com.bosssoft.ecds.entity.vo.subjectvo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bosssoft.ecds.constant.CheckConstant;
import com.bosssoft.ecds.constant.SubjectConstant;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author wy
 * @create 2020-08-10 20:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SubjectVO对象", description="")
public class SubjectVO extends Model<SubjectVO> {

    @ApiModelProperty(value = "科目id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotBlank(message = SubjectConstant.Subject_YEAR_NOT_NULL)
    @ApiModelProperty(value = "年度")
    private String year;

    @NotBlank(message = SubjectConstant.Subject_CODE_NOT_NULL)
    @Pattern(regexp = CheckConstant.INTEGER_REGULAR, message = SubjectConstant.CODE_IS_INTEGER)
    @ApiModelProperty(value = "科目编码")
    private String code;

    @NotBlank(message = SubjectConstant.Subject_NAME_NOT_NULL)
    @Pattern(regexp = CheckConstant.CHINESE_REGULAR, message = SubjectConstant.NAME_IS_CHINESE)
    @ApiModelProperty(value = "科目名称")
    private String name;

    @ApiModelProperty(value = "层级",example = "1")
    private Integer level;

    @ApiModelProperty(value = "是否底级,0否，1是")
    private Boolean leaf;

    @NotNull(message = SubjectConstant.Subject_PID_NOT_NULL)
    @ApiModelProperty(value = "父级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @ApiModelProperty(value = "是否启用，0否，1是")
    private Boolean enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "下一级科目集合")
    private List<SubjectVO> subjectVOS;

}
