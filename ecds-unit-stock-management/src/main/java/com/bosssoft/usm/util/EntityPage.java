package com.bosssoft.usm.util;

import lombok.Data;

import java.util.List;

/**
 * 定义分页查询返回的实体类
 * @author 朱文
 * create on 2020/8/17 16:10
 */
@Data
public class EntityPage {
    /** 定义分页查询返回的实体 **/
    private List<?> data;

    /** 总数目 **/
    private Long tatal;
}
