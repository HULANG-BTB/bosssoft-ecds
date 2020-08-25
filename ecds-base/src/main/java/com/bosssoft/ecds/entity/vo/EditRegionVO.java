package com.bosssoft.ecds.entity.vo;

import lombok.Data;

/**
 * @author: lpb
 * @create: 2020-08-05 14:33
 */
@Data
public class EditRegionVO {

    private String id;

    //区划编码
    private String code;

    //名称
    private String name;

    //助记码
    private String mnem;

    //父区划ID
    private Long parentId;

    //层级
    private Integer level;

    //是否分类
    private boolean assortment;

    //是否为底级
    private boolean leaf;

    //是否启用
    private boolean enabled;

    //是否为系统最高区划
    private boolean topRegion;

    //业务上级区划ID
    private Long busParentId;

    //主管机构名称
    private String orgName;

    private String remark;

}
