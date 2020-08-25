package com.bosssoft.usm.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 退票主表VO
 * @author 朱文
 * create on 2020/8/11 14:36
 */
@Data
@ToString
public class StockReturnVO {

   /**业务单号**/
    private Long no;

    /**审核状态**/
    private Integer changeState;

   /**编制日期**/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    /**退票原因**/
    private String returnReason;

    /**经办人**/
    private String returner;

    /**编制人**/
    private String author;

    /**审核人**/
    private String changeMan;

    /**审核意见**/
    private String changeSitu;

    /**退票单位识别码**/
    private String agenIdCode;

    /**是否提交**/
    private Integer submitStatus;

    /**申退明细VO**/
    private List<StockReturnItemVO> stockReturnItemVOList;

}
