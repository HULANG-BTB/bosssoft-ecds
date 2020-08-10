package com.bosssoft.ecds.entity.vo.incomesortvo;

import com.bosssoft.ecds.constant.CheckConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/6 14:08
 */
@Data
public class AddIncomeSortVO implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -4973539948210269342L;

    /**
     * 收入类别自身id
     */
    @NotNull(message = "收入类别父级id不能为空")
    private Long parentId;
    /**
     * 收入种类编码
     */
    @NotNull(message = "收入类别编码不能为空")
    @Pattern(regexp = CheckConstant.INTEGER_REGULAR, message = "编码只能为数字型字符串")
    private String code;
    /**
     * 收入种类名称
     */
    @NotNull(message = "收入类别名称不能为空")
    @Pattern(regexp = CheckConstant.CHINESE_REGULAR, message = "名称必须全为中文")
    private String name;

    /**
     * 备注信息
     */
    private String remark;
}
