package com.bosssoft.ecds.entity.vo;

import lombok.Data;

/**
 * @ClassName FuzzyQueryVO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/14 9:28
 * @Version 1.0
 */
@Data
public class FuzzyQueryVO {

    /**
     * name : yyy
     */

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
