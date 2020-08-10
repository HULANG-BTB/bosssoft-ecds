package com.bosssoft.ecds.entity.vo;

import lombok.Data;

/**
 * @description: 归口部门查询条件
 * @author: lin.wanning
 * @date: Created in 2020/8/5 20:59
 * @modified By:
 */
@Data
public class FabFinDeptVo {

    /**
     * 名称
     */
    private String fFindeptName;

    /**
     * 是否启用
     */
    private Boolean fIsEnable;


    /**
     * 分页参数，页面大小
     */

    private Integer pageSize;

    /**
     * 分页参数，页码
     */

    private Integer pageNumber;
}
