package com.bosssoft.ecds.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName AccBillDTO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 13:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="AccBillDTO对象", description="票据发放阶段")
public class AccBillDTO {

    @ApiModelProperty(value = "票据校验码")
    private String checkCode;

    @ApiModelProperty(value = "票据序列号")
    private String billBatchId;

    @ApiModelProperty(value = "票据号")
    private String billNo;

    @ApiModelProperty(value = "开具票据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date = new Date();

}
