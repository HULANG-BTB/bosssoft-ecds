package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ube_destroy_apply_item")
public class ItemPo{

    /**
     * 主键
     */
    @TableId(value = "f_id")
    private Long fId;

    /**
     * 父节点ID
     */
    private Long fPid;

    /**
     * 序号
     */
    private Integer fSortNo;

    /**
     * 版本号
     */
    private Integer fVersion;

    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 修改时间
     */
    private LocalDateTime fUpdateTime;

    /**
     * 票据代码
     */
    private String fBillBatchCode;

    /**
     * 起始号
     */
    private String fBillNo1;

    /**
     * 终止号
     */
    private String fBillNo2;

    /**
     * 数量
     */
    private Integer fNumber;

    /**
     * 仓库ID
     */
    private String fWarehouseId;

    /**
     * 仓库名
     */
    private String fWarehouseName;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getfPid() {
        return fPid;
    }

    public void setfPid(Long fPid) {
        this.fPid = fPid;
    }

    public Integer getfSortNo() {
        return fSortNo;
    }

    public void setfSortNo(Integer fSortNo) {
        this.fSortNo = fSortNo;
    }

    public Integer getfVersion() {
        return fVersion;
    }

    public void setfVersion(Integer fVersion) {
        this.fVersion = fVersion;
    }

    public LocalDateTime getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(LocalDateTime fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public LocalDateTime getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(LocalDateTime fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

    public String getfBillBatchCode() {
        return fBillBatchCode;
    }

    public void setfBillBatchCode(String fBillBatchCode) {
        this.fBillBatchCode = fBillBatchCode;
    }

    public String getfBillNo1() {
        return fBillNo1;
    }

    public void setfBillNo1(String fBillNo1) {
        this.fBillNo1 = fBillNo1;
    }

    public String getfBillNo2() {
        return fBillNo2;
    }

    public void setfBillNo2(String fBillNo2) {
        this.fBillNo2 = fBillNo2;
    }

    public Integer getfNumber() {
        return fNumber;
    }

    public void setfNumber(Integer fNumber) {
        this.fNumber = fNumber;
    }

    public String getfWarehouseId() {
        return fWarehouseId;
    }

    public void setfWarehouseId(String fWarehouseId) {
        this.fWarehouseId = fWarehouseId;
    }

    public String getfWarehouseName() {
        return fWarehouseName;
    }

    public void setfWarehouseName(String fWarehouseName) {
        this.fWarehouseName = fWarehouseName;
    }
}
