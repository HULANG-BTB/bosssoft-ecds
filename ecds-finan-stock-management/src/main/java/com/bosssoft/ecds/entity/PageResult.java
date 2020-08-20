package com.bosssoft.ecds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangxiaohui
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

    private List<?> row;
}
