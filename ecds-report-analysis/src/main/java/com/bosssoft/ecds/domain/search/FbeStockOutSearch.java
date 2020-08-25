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
 * 出库查询条件类
 * @author 12964
 * @return
 * @date 2020/8/24 9:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "出库查询的条件类")
public class FbeStockOutSearch {

    /**
     * 票据名称
     */
    @ApiModelProperty(value = "序号")
    private String billName;

    /**
     * 起始和终止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private List<Date> dateList;
}
