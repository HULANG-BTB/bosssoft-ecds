package com.bosssoft.ecds.domain.search;

import java.io.Serializable;
import lombok.Data;

/**
 * 生命周期查询条件类，封装info传递的参数
 * @author 12964
 * @date 2020/8/24 9:06
 */
@Data
public class LifeSearch implements Serializable {
    /**
     * 票据编码（18位）
     */
    private String code;
}
