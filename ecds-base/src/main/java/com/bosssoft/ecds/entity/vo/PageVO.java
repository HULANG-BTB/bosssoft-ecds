package com.bosssoft.ecds.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName PageVo
 * @Author AloneH
 * @Date 2020/7/26 10:00
 * @Description VO实体
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PageVO对象")
public class PageVO extends Model<PageVO> {

    /**
     * 查询数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private transient List<Object> items;

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
     * 关键字
     */
    @ApiModelProperty(value = "关键字", required = false, example = "")
    private String keyword;


}
