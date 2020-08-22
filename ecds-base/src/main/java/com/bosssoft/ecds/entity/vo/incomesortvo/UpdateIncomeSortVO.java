package com.bosssoft.ecds.entity.vo.incomesortvo;

import com.bosssoft.ecds.constant.CheckConstant;
import com.bosssoft.ecds.constant.IncomeSortConstant;
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
    @NotNull(message = IncomeSortConstant.INCOME_SORT_ID_NOT_NULL)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 收入种类名称
     */
    @NotNull(message = IncomeSortConstant.INCOME_SORT_NAME_NOT_NULL)
    @Pattern(regexp = CheckConstant.CHINESE_REGULAR, message = IncomeSortConstant.NAME_IS_CHINESE)
    private String name;
    /**
     * 收入种类编码
     */
    @NotNull(message = IncomeSortConstant.INCOME_SORT_CODE_NOT_NULL)
    @Pattern(regexp = CheckConstant.INTEGER_REGULAR, message = IncomeSortConstant.CODE_IS_INTEGER)
    private String code;
    /**
     * 是否底级
     */
    @NotNull(message = IncomeSortConstant.LEAF_NOT_NULL)
    private Boolean leaf;
    /**
     * 备注信息
     */
    private String remark;

}
