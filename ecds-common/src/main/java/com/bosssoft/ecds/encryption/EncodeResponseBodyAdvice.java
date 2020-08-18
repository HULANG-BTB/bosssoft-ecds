package com.bosssoft.ecds.encryption;


import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.util.AESUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author: Jianbinbing
 * @Date: 2020/8/17 11:42
 */

@Slf4j
@RestControllerAdvice
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //只有@SecretAnnotation的方法才会触发该类
        return methodParameter.hasMethodAnnotation(SecretAnnotation.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        boolean encode = false;
        if (methodParameter.getMethod().isAnnotationPresent(SecretAnnotation.class)) {
            //获取注解配置的包含和去除字段
            SecretAnnotation serializedField = methodParameter.getMethodAnnotation(SecretAnnotation.class);
            //出参是否需要加密
            encode = serializedField.encode();
        }
        /**
         * 加密开始
         */
        if (encode) {
            ObjectMapper objectMapper = new ObjectMapper();
            //request对象

            try {
                String key = AESUtil.getKey();
                log.info("AES的key：" + key);
                String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
                log.info("需要加密的data数据：" + result);
                String data = AESUtil.encrypt(result, key);
                //用前端的公钥来加密AES的key，并转成Base64
//                String aesKey = Base64.encodeBase64String(RSAUtil.encryptByPublicKey(key.getBytes(), publicKey));

                return new QueryResponseResult(CommonCode.SUCCESS, objectMapper.readValue("{\"data\":\"" + data + "\",}", Object.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return body;
    }

}
