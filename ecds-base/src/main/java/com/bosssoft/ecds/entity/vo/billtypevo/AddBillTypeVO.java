package com.bosssoft.ecds.entity.vo.billtypevo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import static com.bosssoft.ecds.constant.BillTypeConstant.*;


/**
 * @author :Raiz
 * @date :2020/8/5
 */
@Data
public class AddBillTypeVO {

    @NotBlank(message = CODE_NOT_BLANK)
    String code;

    @NotBlank(message = NAME_NOT_BLANK)
    String name;

    @NotBlank(message = BILL_NATURE_NOT_BLANK)
    String billNature;

    @NotBlank(message = MEMORY_CODE_NOT_BLANK)
    String memoryCode;

    @NotNull(message = EFF_DATE_NOT_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date effDate;

    @NotNull(message = EXP_DATE_NOT_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date expDate;

    @NotNull(message = CHECK_SORT_NOT_NULL)
    Integer checkSort;

    @NotNull(message = NATURE_CODE_NOT_NULL)
    Integer natureCode;

    Integer checkQuota;

    BigDecimal quotaAmount;

    Integer safeYear;

    String remark;

    Long pid;

}
