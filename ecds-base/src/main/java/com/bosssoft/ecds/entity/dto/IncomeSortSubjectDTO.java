package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * @author wy
 * @create 2020-08-10 20:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IncomeSortSubjectDTO对象", description="")
public class IncomeSortSubjectDTO extends Model<IncomeSortSubjectDTO> {
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "收入类别id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long incomeSortId;

    @ApiModelProperty(value = "预算科目id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;

}
