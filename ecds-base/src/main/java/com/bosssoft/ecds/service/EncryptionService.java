package com.bosssoft.ecds.service;


import com.bosssoft.ecds.entity.vo.RSAPublicKeyVO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/19 9:26
 */
public interface EncryptionService {

    /**
     * 获取后端的公钥
     *
     * @return
     */
    QueryResponseResult getRSAPublicKey();

    /**
     * 增加前端公钥，并放入redis中
     *
     * @param
     * @return
     */
    ResponseResult addRSAPublicKey(RSAPublicKeyVO rsaPublicKeyVO);

}
