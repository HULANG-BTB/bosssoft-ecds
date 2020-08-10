package com.bosssoft.ecds.entity.vo.incomesortvo;


import com.bosssoft.ecds.constant.CheckConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/5 20:14
 */
@Data
public class FuzzyQueryIncomeSortVO implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4973539948210269343L;

    /**
     * 收入种类编码
     */
    @Pattern(regexp = CheckConstant.INTEGER_REGULAR, message = "编码只能为数字型字符串")
    private String code;
    /**
     * 收入种类名称
     */
    @Pattern(regexp = CheckConstant.CHINESE_REGULAR, message = "名称必须全为中文")
    private String name;
    /**
     * 每页条数
     */
    @NotNull(message = "页面显示数据大小不能为空")
    private Integer size;
    /**
     * 页码
     */
    @NotNull(message = "当前页码不能为空")
    private Integer page;
}
