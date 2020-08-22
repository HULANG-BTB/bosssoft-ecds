package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: lpb
 * @create: 2020-08-05 10:53
 */
@Data
public class RegionExt {

    private Long id;

    private String code;

    private String name;

    private Long parentId;

    private boolean assortment;

    private List<RegionExt> children;

}
