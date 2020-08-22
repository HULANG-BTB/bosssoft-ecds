package com.bosssoft.ecds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 分页
 * </p>
 *
 * @author misheep
 * @since 2020-08-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {

    /**
     * 总页码
     */
    private Long total;

    /**
     * 每页记录数
     */
    private Long limit;

    /**
     * 当前页码
     */
    private Long page;

    /**
     * 数据data
     */
    private List<?> row;
}
