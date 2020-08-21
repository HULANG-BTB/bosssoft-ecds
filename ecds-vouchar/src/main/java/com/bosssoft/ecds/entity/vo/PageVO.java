package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @param <T>
 * @author liuke
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PageVO对象")
public class PageVO<T> {
    /**
     * 查询数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<T> data;

    /**
     * 总数
     */
    @ApiModelProperty(value = "数据总条数")
    private Long total;

    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "分页限制", example = "10")
    private Long limit;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页码", example = "1")
    private Long page;


}

