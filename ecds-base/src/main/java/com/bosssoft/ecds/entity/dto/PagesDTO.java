package com.bosssoft.ecds.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @Author asus
 * @create 14/8/2020 下午2:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PagesDTO <T> {

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
    private Map<String,String> keyword;

}
