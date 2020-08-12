package com.bosssoft.ecds.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/12 9:07
 */
@Data
public class IncomeSubjectDTO {
    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 收入种类编码
     */
    private String code;
    /**
     * 收入种类名称
     */
    private String name;

    private List<IncomeSubjectDTO> incomeSubjectDTOList;


}
