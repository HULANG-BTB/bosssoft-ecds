package com.bosssoft.ecds.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lpb
 * @create: 2020-08-05 10:47
 */
@Data
@NoArgsConstructor
public class QueryRegionRequestVO {

    private Long id;

    private Long parentId;

    private String code;

    private String name;

}
