package com.bosssoft.usm.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 张东海
 * @date 2020/8/10
 * @description
 */
@Data
@TableName("test")
public class Test {
    @TableId
    private Integer id;
    @TableField
    private String name;
}
