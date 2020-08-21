package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class WriteOffDetailDTO {

    // 开票总览
    List<WriteOffInvoceDetailDTO> writeOffInvoceDetailDTOList;

    // 收入情况
    List<WriteOffIncomeDetailDTO> writeOffIncomeDetailDTOList;

    // 开票明细
    List<WriteOffBillInvDetailDTO> writeOffBillInvDetailDTOList;

    // 预警记录

}
