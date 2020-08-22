package com.bosssoft.ecds.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author liuke
 * @className PageDTO
 * @date 2020/8/13 15:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageDTO<T> {
    /**
     * 查询数据列表
     */
    private List<T> data;

    /**
     * 总数
     */
    private Long total;

    /**
     * 每页显示条数，默认 10
     */
    private Long limit;

    /**
     * 当前页
     */
    private Long page;

}

