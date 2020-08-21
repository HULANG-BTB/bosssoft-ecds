package com.bosssoft.ecds.entity.vo;

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
@ApiModel(value = "StockOutPageVo对象", description = "StockOutPageVo对象")
public class StockOutPageVo extends Model<StockOutPageVo> {

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
    private Long id;

    /**
     * 编制人
     */
    private String author;

    /**
     * 审核状态(0未1提交2通过3退回）
     */
    private Integer changeState;

    /**
     * 时间区间
     */
    private List<Date> period;
}
