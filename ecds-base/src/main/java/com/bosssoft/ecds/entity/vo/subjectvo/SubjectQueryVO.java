package com.bosssoft.ecds.entity.vo.subjectvo;

import com.bosssoft.ecds.constant.SubjectConstant;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author shkstart
 * @create 2020-08-13 16:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SubjectQueryVO对象", description="")
public class SubjectQueryVO {

    @NotNull(message = SubjectConstant.Subject_ID_NOT_NULL)
    @ApiModelProperty(value = "左侧菜单预算科目的id，即父id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotBlank(message = SubjectConstant.Subject_YEAR_NOT_NULL)
    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "科目名称")
    private String name;

    @Min(value = 1L, message = "页码最小为1")
    @NotNull(message = SubjectConstant.PAGE_NUM_NOT_NULL)
    @ApiModelProperty(value = "当前页码",example = "1")
    private Integer page;

    @Min(value = 1L, message = "每页数量最小为1")
    @NotNull(message = SubjectConstant.PAGE_SIZE_NOT_NULL)
    @ApiModelProperty(value = "每页数量",example = "10")
    private Integer limit;


}
