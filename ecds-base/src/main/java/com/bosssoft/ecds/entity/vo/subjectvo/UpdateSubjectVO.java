package com.bosssoft.ecds.entity.vo.subjectvo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bosssoft.ecds.constant.SubjectConstant;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;


/**
 * @author shkstart
 * @create 2020-08-14 22:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UpdateSubjectVO对象", description="")
public class UpdateSubjectVO extends Model<UpdateSubjectVO> {

    @NotNull(message = SubjectConstant.Subject_ID_NOT_NULL)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "科目id")
    private Long id;

    @ApiModelProperty(value = "是否启用，0否，1是")
    private Boolean enable;

    @ApiModelProperty(value = "备注")
    private String remark;
}