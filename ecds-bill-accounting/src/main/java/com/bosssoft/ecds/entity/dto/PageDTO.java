package com.bosssoft.ecds.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @ClassName PageDTO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/13 15:03
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageDTO<T> {
    /**
     * 查询数据列表
     */
    private List<T> items;

    /**
     * 总数
     */
    private long total;

    /**
     * 每页显示条数，默认 10
     */
    private long limit;

    /**
     * 当前页
     */
    private long page;

    /**
     * 关键字
     */
    private String keyword;
}
