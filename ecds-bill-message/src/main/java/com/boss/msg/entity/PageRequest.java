package com.boss.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangxiaohui
 * @create 2020/8/10 16:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest implements Serializable {

    /**
     * 每页显示大小
     */
    private long  size;

    /**
     * 当前页码
     */
    private  long current;

    /**
     * 最大页数
     */
    private  long maxCurrent;

    /**
     * 数据总条数
     */
    private  long total;

    public void setTotal(long total) {
        if(size != 0){
            if(total % size != 0){
                maxCurrent = total / size + 1;
            }else {
                maxCurrent = total / size;
            }
        }
    }
}
