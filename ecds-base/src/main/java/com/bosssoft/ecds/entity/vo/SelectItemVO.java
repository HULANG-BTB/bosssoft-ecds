package com.bosssoft.ecds.entity.vo;

import com.bosssoft.ecds.entity.dto.PageDTO;
import lombok.Data;

/**
 * @ClassName :  SelectItemVO
 * @Description : 查询与票据种类无关的项目
 * @Author : wuliming
 * @Date: 2020-08-11 09:34
 */
@Data
public class SelectItemVO {
    /**
     * 每页显示数量
     */
    private Integer pageSize;
    /**
     * 请求的页数
     */
    private Integer pageNum;
    /**
     * 票据种类编码
     */
    private String billCode;
    /**
     * 项目名称
     */
    private String itemName;
}
