package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.entity.vo.RSAPublicKeyVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.EncryptionService;
import com.bosssoft.ecds.util.RSAUtil;
import com.bosssoft.ecds.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/19 9:27
 */
@Service
@Slf4j
public class EncryptionServiceImpl implements EncryptionService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public QueryResponseResult getRSAPublicKey() {
        QueryResponseResult queryResponseResult = new com.bosssoft.ecds.response.QueryResponseResult(CommonCode.SUCCESS, RSAUtil.getPublicKey());
        return queryResponseResult;
    }

    @Override
    public ResponseResult addRSAPublicKey(RSAPublicKeyVO rsaPublicKeyVO) {
        redisUtil.set("publicKey", rsaPublicKeyVO.getPublicKey());
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
