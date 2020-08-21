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
import org.springframework.beans.factory.annotation.Value;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotBlank(message = SubjectConstant.Subject_NAME_NOT_NULL)
    @Pattern(regexp = CheckConstant.CHINESE_REGULAR, message = SubjectConstant.NAME_IS_CHINESE)
    @ApiModelProperty(value = "科目名称")
    private String name;

    @Value("true")
    @ApiModelProperty(value = "是否启用，0否，1是")
    private Boolean enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Value("false")
    @ApiModelProperty(value = "是否底级,0否，1是")
    private Boolean leaf;
}
