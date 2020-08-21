package com.bosssoft.ecds.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author :Raiz
 * @date :2020/8/7
 */
@Data
public class BillTypeTableDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    Long id;

    String code;

    String name;

    String billNature;

    String memoryCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date effDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date expDate;

    Integer checkSort;

    String units;

    Integer checkQuota;

    BigDecimal quotaAmount;

    Integer safeYear;

    Integer minStock;

    String remark;

    Integer level;

    Long pid;

    Integer checkLeaf;

    Integer natureCode;

    String typeCode;

    String parentCode;

    String billWay;
}
