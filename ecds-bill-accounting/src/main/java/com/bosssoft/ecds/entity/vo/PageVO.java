package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName PageVO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/13 15:04
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PageVO对象")
public class PageVO< T > {
    /**
     * 查询数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<Object> items;

    /**
     * 总数
     */
    @ApiModelProperty(value = "数据总条数")
    private long total;

    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "分页限制", example = "10")
    private long limit;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页码", example = "1")
    private long page;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字", example = "")
    private String keyword;

    /**
     * 根据类型排序(不同页面可以有不同的应用)
     */
    @ApiModelProperty(value = "类型")
    private Integer accountType;

    /**
     * 根据主键/升序降序
     * 由于主键使用雪花算法，故按主键升降即按照时间先后顺序排序
     */
    @ApiModelProperty(value = "主键排序方式")
    private String sort;

}
