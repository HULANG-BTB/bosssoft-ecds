package com.bosssoft.ecds.entity.vo.incomesortvo;

import com.bosssoft.ecds.constant.CheckConstant;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/6 10:10
 */
@Data
public class UpdateIncomeSortVO implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4973539948210269346L;

    /**
     * 收入类别自身id
     */
    @NotNull(message = "收入类别ID不能为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 收入种类名称
     */
    @NotNull(message = "收入类别名称不能为空")
    @Pattern(regexp = CheckConstant.CHINESE_REGULAR, message = "名称必须全为中文")
    private String name;
    /**
     * 是否底级
     */
    @NotNull(message = "是否底级不能为空")
    private Boolean leaf;
    /**
     * 备注信息
     */
    private String remark;

}
