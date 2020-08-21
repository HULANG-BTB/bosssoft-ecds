package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.constant.EncryptionConstant;
import com.bosssoft.ecds.entity.vo.RSAPublicKeyVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.EncryptionService;
import com.bosssoft.ecds.util.RedisUtils;
import com.bosssoft.ecds.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/19 9:27
 */
@Service
@Slf4j
public class EncryptionServiceImpl implements EncryptionService {
    @Autowired
    private RedisUtils redisUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public QueryResponseResult getRSAPublicKey() {
        Long userId = 123L;
        Map<String, Object> rsa = RSAUtil.genKeyPair();
        String publicKey = RSAUtil.getPublicKey(rsa);
        redisUtil.set(userId + EncryptionConstant.PRIVATE_KEY, RSAUtil.getPrivateKey(rsa), 600);
        return new QueryResponseResult(CommonCode.SUCCESS, publicKey);
    }

    @Override
    public ResponseResult addRSAPublicKey(RSAPublicKeyVO rsaPublicKeyVO) {
        Long userId = 123L;
        redisUtil.set(userId + EncryptionConstant.PUBLIC_KEY, rsaPublicKeyVO.getPublicKey(), 600);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}