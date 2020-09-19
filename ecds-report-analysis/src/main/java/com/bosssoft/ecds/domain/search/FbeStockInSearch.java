package com.bosssoft.ecds.domain.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 入库查询条件类，用不到
 * @author 12964
 * @date 2020/8/24 9:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "入库查询的条件类")
public class FbeStockInSearch {
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long fNo;

    /**
     * 票据名称
     */
    @ApiModelProperty(value = "票据名称")
    private String fBillName;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private int fNumber;

    /**
     * 起始和终止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "起始和终止日期")
    private List<Date> date;
}
