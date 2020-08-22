package com.bosssoft.usm.entity.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 分页相关参数VO
 * @author 朱文
 * create on 2020/8/11 17:08
 */
@Data
@ToString
public class PageVO {

    /** 当前页 **/
    private Integer pageNum;

    /** 每页大小 **/
    private Integer pageSize;

}
