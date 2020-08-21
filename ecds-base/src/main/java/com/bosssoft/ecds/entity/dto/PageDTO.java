package com.bosssoft.ecds.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @ClassName PageDTO
 * @Author AloneH
 * @Date 2020/7/26 10:04
 * @Description
 * 实体类 DTO
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class PageDTO <T> {

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
