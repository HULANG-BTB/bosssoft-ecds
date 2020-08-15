package com.bosssoft.ecds.entity.vo.itemvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 吴志鸿
 * @date 2020/8/13
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PageVO对象")
public class ItemPageVO<T> {
    /**
     * 查询数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private transient List<T> items;

    /**
     * 总数
     */
    @ApiModelProperty(value = "数据总条数")
    private long total;

    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "分页限制", example = "10")
    @NotNull(message = "分页大小不能为空.")
    @Min(value = 1L, message = "分页大小.")
    private long limit;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页码", example = "1")
    @NotNull(message = "当前页不能为空.")
    @Min(value = 1L, message = "页码范围错误.")
    private long page;

    /**
     * 通过名字进行模糊查询
     */
    @ApiModelProperty(value = "模糊查询", required = false, example = "")
    private String keyword;

    /**
     * 通过审核状态进行筛选查询
     */
    @ApiModelProperty(value = "通过审核状态进行筛选查询", required = false, example = "")
    private Integer isenable;
}
