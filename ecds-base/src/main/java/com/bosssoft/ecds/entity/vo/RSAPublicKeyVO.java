package com.bosssoft.ecds.entity.vo;

import com.bosssoft.ecds.constant.IncomeSortConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/19 10:40
 */
@Data
public class RSAPublicKeyVO implements Serializable {

    /**
     * 前端公钥
     */
    @NotNull(message = IncomeSortConstant.PUBLIC_KEY_NOT_NULL)
    private String publicKey;

}
