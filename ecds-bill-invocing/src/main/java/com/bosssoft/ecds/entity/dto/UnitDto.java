package com.bosssoft.ecds.entity.dto;

import lombok.Data;

@Data
public class UnitDto {

    /**
     * 区划ID
     */
    private String fRgnCode;

    /**
     * 单位编码
     */
    private String fAgenIdCode;

    /**
     * 开票点ID
     */
    private String fPlaceId;

    /**
     * 开票点编码
     */
    private String fPlaceCode;

    /**
     * 开票点名称
     */
    private String fPlaceName;
}
