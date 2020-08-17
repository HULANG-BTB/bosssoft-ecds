package com.bosssoft.ecds.utils.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 分页查询时使用到的数据类型
 * list存储分页的数据，total存储数据总条数
 *
 * @param <T>
 * @author liuke
 */
@Data
@ToString
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}

