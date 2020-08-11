package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/5 14:09
 */
@Data
public class IncomeSortDTO {

    /**
     * 收入种类id
     */
    private Long id;
    /**
     * 收入种类编码
     */
    private String code;
    /**
     * 收入种类名称
     */
    private String name;
    /**
     * 是否底级
     */
    private Boolean leaf;
    /**
     * 级次
     */
    private Integer level;
    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 收入种类子级
     */
    private List<IncomeSortDTO> incomeSortDTOList;


}
