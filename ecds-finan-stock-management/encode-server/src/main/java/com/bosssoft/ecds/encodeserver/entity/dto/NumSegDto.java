package com.bosssoft.ecds.encodeserver.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 黄杰峰
 * @Date 2020/8/6 0006 16:29
 * @Description 票号段实体类
 */
@Data
public class NumSegDto implements Serializable {

    private long beginCode;

    private long endCode;
}
