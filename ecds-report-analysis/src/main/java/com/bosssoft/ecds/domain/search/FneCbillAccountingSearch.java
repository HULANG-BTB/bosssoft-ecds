package com.bosssoft.ecds.domain.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @ClassName FneCbillAccountingSearch
 * @Description FneCbillAccountingDTO
 * @auther wangpeng
 * @Date 2020/8/19 15:16
 * @Version 1.0
 **/
@Data
public class FneCbillAccountingSearch {

    /**
     * 入账时间范围
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value = "入账时间范围")
    private List<Date> fTimeList;

    @ApiModelProperty(value = "入账单位类型，开票单位(值=0) 缴款单位(值=1)")
    private Integer fType;

    @ApiModelProperty(value = "缴款人电话")
    private String fPayerTel;

    @ApiModelProperty(value = "单位名称")
    private String fAgenName;

}
