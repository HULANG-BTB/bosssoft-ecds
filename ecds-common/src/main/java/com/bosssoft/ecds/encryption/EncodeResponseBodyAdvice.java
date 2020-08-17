package com.bosssoft.ecds.encryption;


import com.bosssoft.ecds.util.AESUtils;
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
            try {
                String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
                return AESUtils.encrypt(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return body;
    }

}
