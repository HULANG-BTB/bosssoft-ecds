package com.bosssoft.ecds.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PageVo
 * @Author AloneH
 * @Date 2020/7/26 10:00
 * @Description VO实体
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class PageVO implements Serializable {

    /**
     * 查询数据列表
     */
    private List<Object> items;

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
