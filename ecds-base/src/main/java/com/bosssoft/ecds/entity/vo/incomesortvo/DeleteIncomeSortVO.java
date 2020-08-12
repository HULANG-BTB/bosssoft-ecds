package com.bosssoft.ecds.entity.vo.incomesortvo;

import com.bosssoft.ecds.constant.IncomeSortConstant;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/12 10:27
 */
@Data
public class DeleteIncomeSortVO {

    /**
     * 收入类别自身id
     */
    @NotNull(message = IncomeSortConstant.INCOME_SORT_ID_NOT_NULL)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
}
