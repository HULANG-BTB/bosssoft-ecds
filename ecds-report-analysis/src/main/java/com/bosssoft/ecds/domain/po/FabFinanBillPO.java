package com.bosssoft.ecds.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import com.bosssoft.ecds.domain.vo.FabFinanBillVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * fab_finan_bill
 * @author syf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("财政票据")
@TableName("fab_finan_bill")
public class FabFinanBillPO implements Serializable {
    /**
     * 财政票据表_主键
     */
    @TableId(type=IdType.AUTO)
    private Long fId;

    /**
     * 票据编码（18位）
     */
    @ApiModelProperty("票据编码")
    private String fBillCode;

    /**
     * 票据代码（8位）
     */
    private String fBillPrecode;

    /**
     * 票据ID（10）
     */
    private String fBillId;

    /**
     * 仓库ID
     */
    private Long fWarehouseId;

    /**
     * 票据名称
     */
    private String fBillName;

    /**
     * 单位编码
     */
    private Long fAgenCode;

    /**
     * 单位名称
     */
    private String fAgenName;

    /**
     * 生效日期
     */
    private Date fEffDate;

    /**
     * 失效日期
     */
    private Date fExpDate;

    /**
     * 经办人ID
     */
    private Long fOperId;

    /**
     * 经办人
     */
    private String fOperator;

    /**
     * 经办日期
     */
    private Date fOpeDate;

    /**
     * 是否发放
     */
    private Integer fIsPutout;

    /**
     * 是否退票
     */
    private Integer fIsReturn;

    /**
     * 是否作废
     */
    private Integer fIsInvalid;

    /**
     * 创建时间
     */
    private Date fCreateTime;

    /**
     * 最后修改时间
     */
    private Date fUpdateTime;

    /**
     * 版本号（乐观锁）
     */
    @Version
    private Integer fVersion;

    /**
     * 区划编码
     */
    private String fRgnCode;

    /**
     * 核销状态（0未核销1需要核销2不需核销）
     */
    private Integer fHxStatus;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean fLogicDelete;

    private static final long serialVersionUID = 1L;


    public static FabFinanBillPO VoToPo(FabFinanBillVO o) {
        if (o == null) {
            return null;
        }
        FabFinanBillPO fabFinanBillPo = new FabFinanBillPO();
        fabFinanBillPo.setFBillCode(o.getFBillCode());
        fabFinanBillPo.setFBillPrecode(o.getFBillPrecode());
        fabFinanBillPo.setFBillId(o.getFBillId());
        fabFinanBillPo.setFWarehouseId(o.getFWarehouseId());
        fabFinanBillPo.setFBillName(o.getFBillName());
        fabFinanBillPo.setFAgenCode(o.getFAgenCode());
        fabFinanBillPo.setFAgenName(o.getFAgenName());
        fabFinanBillPo.setFEffDate(o.getFEffDate());
        fabFinanBillPo.setFExpDate(o.getFExpDate());
        fabFinanBillPo.setFOperId(o.getFOperId());
        fabFinanBillPo.setFOperator(o.getFOperator());
        fabFinanBillPo.setFOpeDate(o.getFOpeDate());
        fabFinanBillPo.setFIsPutout(o.getFIsPutout());
        fabFinanBillPo.setFIsReturn(o.getFIsReturn());
        fabFinanBillPo.setFIsInvalid(o.getFIsInvalid());
        fabFinanBillPo.setFCreateTime(o.getFCreateTime());
        fabFinanBillPo.setFUpdateTime(o.getFUpdateTime());
        fabFinanBillPo.setFRgnCode(o.getFRgnCode());
        fabFinanBillPo.setFHxStatus(o.getFHxStatus());
        return fabFinanBillPo;
    }

    public static FabFinanBillVO PoToVo(FabFinanBillPO o) {
        if (o == null) {
            return null;
        }
        FabFinanBillVO fabFinanBillVO = new FabFinanBillVO();
        fabFinanBillVO.setFBillCode(o.getFBillCode());
        fabFinanBillVO.setFBillPrecode(o.getFBillPrecode());
        fabFinanBillVO.setFBillId(o.getFBillId());
        fabFinanBillVO.setFWarehouseId(o.getFWarehouseId());
        fabFinanBillVO.setFBillName(o.getFBillName());
        fabFinanBillVO.setFAgenCode(o.getFAgenCode());
        fabFinanBillVO.setFAgenName(o.getFAgenName());
        fabFinanBillVO.setFEffDate(o.getFEffDate());
        fabFinanBillVO.setFExpDate(o.getFExpDate());
        fabFinanBillVO.setFOperId(o.getFOperId());
        fabFinanBillVO.setFOperator(o.getFOperator());
        fabFinanBillVO.setFOpeDate(o.getFOpeDate());
        fabFinanBillVO.setFIsPutout(o.getFIsPutout());
        fabFinanBillVO.setFIsReturn(o.getFIsReturn());
        fabFinanBillVO.setFIsInvalid(o.getFIsInvalid());
        fabFinanBillVO.setFCreateTime(o.getFCreateTime());
        fabFinanBillVO.setFUpdateTime(o.getFUpdateTime());
        fabFinanBillVO.setFRgnCode(o.getFRgnCode());
        fabFinanBillVO.setFHxStatus(o.getFHxStatus());
        return fabFinanBillVO;
    }
}