package com.bosssoft.ecds.domain.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author syf
 * @Date 2020/8/15 17:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabFinanBillSearch {

    private String type;

    private String billName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private List<Date> dateList;
}
