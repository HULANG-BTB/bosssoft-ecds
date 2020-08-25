package com.bosssoft.ecds.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author syf
 * @Date 2020/8/20 14:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDestroyVO {
    private Integer total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;
}
