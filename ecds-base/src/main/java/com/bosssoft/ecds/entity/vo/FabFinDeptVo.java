package com.bosssoft.ecds.entity.vo;

import com.bosssoft.ecds.entity.po.FabFinDept;
import lombok.Data;

import java.util.List;

/**
 * @description: 归口部门查询条件
 * @author: lin.wanning
 * @date: Created in 2020/8/5 20:59
 * @modified By:
 */
@Data
public class FabFinDeptVo extends FabFinDept {
    /**
     * 前端传来的区划数组
     */

    private List<String> rgnCodeArray;


    /**
     * 分页参数，页面大小
     */

    private Integer pageSize;

    /**
     * 分页参数，页码
     */

    private Integer pageNumber;
}
