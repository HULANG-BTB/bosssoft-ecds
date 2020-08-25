package com.bosssoft.ecds.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 出库信息前台展示VO类
 * @author 12964
 * @return
 * @date 2020/8/24 15:47
 */
@Data
public class FbeStockOutVO implements Serializable {
    /**
     * 出库表id
     */
    private Long fPid;

    /**
     * 序号
     */
    private Long fNo;

    /**
     * 票据代码（8位）
     */
    private String fBillPrecode;

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
     * 最后修改时间
     */
    private Date fUpdateTime;
}
