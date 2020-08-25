package com.bosssoft.ecds.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OutChangePageVo对象", description = "OutChangePageVo")
public class OutChangePageVo extends Model<OutChangePageVo> {

    /**
     * 当前页码
     */
    private Long page;

    /**
     * 每页记录数
     */
    private Long limit;

    /**
     * 总数
     */
    private Long total;

    /**
     * 出库主键
     */
    private Long pid;

    /**
     * 操作者(编制人)
     */
    private String operator;

    /**
     * 变更状态(0无用1新增2修改3删除)
     */
    private Integer altercode;
    /**
     * 时间区间
     */
    private List<Date> period;
}
