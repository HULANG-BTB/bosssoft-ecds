package com.bosssoft.ecds.domain.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入库信息前台展示VO类
 * @author 12964
 * @return
 * @date 2020/8/24 15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "入库的VO对象，展示到前端部分")
public class FbeStockInVO implements Serializable {
    /**
     * 票据代码（8位）
     */
    private String fBillCode;

    /**
     * 票据名称
     */
    private String fBillName;

    /**
     * 数量
     */
    private Integer fNumber;

    /**
     * 起始号
     */
    private String fBillNo1;

    /**
     * 终止号
     */
    private String fBillNo2;

    /**
     * 创建时间
     */
    private Date fCreateTime;

    /**
     * 操作者name
     */
    private String fOperator;
}
