package com.bosssoft.ecds.entity.vo;

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

    private int pageNum;

    private int pageSize;

}
