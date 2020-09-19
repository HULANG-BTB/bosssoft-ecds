package com.bosssoft.ecds.domain.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author syf
 * @Date 2020/8/15 17:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FbbStockDestroySearch {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    private String billType;
}
