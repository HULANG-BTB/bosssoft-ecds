package com.bosssoft.ecds.encryption;


import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.util.RedisUtils;
import com.bosssoft.ecds.utils.AESUtil;
import com.bosssoft.ecds.utils.RSAUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 拦截方法体上有@Encrypt的方法
 *
 * @author: Jianbinbing
 * @Date: 2020/8/17 11:42
 */

@Slf4j
@RestControllerAdvice
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {
    @Autowired
    private RedisUtils redisUtil;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //只有@Encrypt的方法才会触发该类
        return methodParameter.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        /**
         * 加密开始
         */
        ObjectMapper objectMapper = new ObjectMapper();
        //request对象
        try {
            //获取对称密钥
            String key = AESUtil.getKey();
            //获取需要加密的数据
            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            //ase加密
            String data = AESUtil.encrypt(result, key);
            String publicKey = (String) redisUtil.get("publicKey");
            //用前端的公钥来加密AES的key，并转成Base64
            String aesKey = RSAUtil.encryptByPublicKey(key.getBytes(), Base64.decodeBase64(publicKey));
            return new QueryResponseResult(CommonCode.SUCCESS, data, aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }


}
