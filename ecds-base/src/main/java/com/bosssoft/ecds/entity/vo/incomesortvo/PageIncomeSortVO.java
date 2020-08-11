package com.bosssoft.ecds.entity.vo.incomesortvo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/6 10:59
 */
@Data
public class PageIncomeSortVO implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -4973539948210269344L;

    /**
     * 当前收入类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 每页条数
     */
    @NotNull(message = "页面显示数据大小不能为空")
    private Integer size;
    /**
     * 页码
     */
    @NotNull(message = "当前页码不能为空")
    private Integer page;

}
