package com.bosssoft.ecds.entity.vo.subjectvo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bosssoft.ecds.constant.CheckConstant;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
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
    private Long id;

    @NotNull(message = "年度不能为空")
    @ApiModelProperty(value = "年度")
    private String year;

    @NotNull(message = "科目类别编码不能为空")
    @Pattern(regexp = CheckConstant.INTEGER_REGULAR, message = "编码只能为数字型字符串")
    @ApiModelProperty(value = "科目编码")
    private String code;

    @NotNull(message = "科目类别名称不能为空")
    @Pattern(regexp = CheckConstant.CHINESE_REGULAR, message = "名称必须全为中文")
    @ApiModelProperty(value = "科目名称")
    private String name;

    @NotNull(message = "层级不能为空")
    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "是否底级")
    private Boolean leaf;

    @NotNull(message = "科目类别父级id不能为空")
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
