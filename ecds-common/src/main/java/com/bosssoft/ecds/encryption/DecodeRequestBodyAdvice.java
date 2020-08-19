package com.bosssoft.ecds.encryption;


import com.alibaba.fastjson.JSONObject;
import com.bosssoft.ecds.exception.CustomException;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.util.AESUtil;
import com.bosssoft.ecds.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;


/**
 * 拦截方法体上有@Decrypt的方法
 *
 * @author: Jianbinbing
 * @Date: 2020/8/17 11:39
 */
@Slf4j
@RestControllerAdvice
public class DecodeRequestBodyAdvice implements RequestBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        //只有@SecretAnnotation的方法才会触发该类
        return methodParameter.hasMethodAnnotation(Decrypt.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            //获取请求入参 可以从这里获取流
            InputStream inputStream = inputMessage.getBody();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        //获取请求数据
        String builderString = stringBuilder.toString();
        try {
            return new MyHttpInputMessage(inputMessage.getHeaders(), builderString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {
            return Class.forName(targetType.getTypeName()).newInstance();
        } catch (Exception e) {
            return body;

        }
    }

    /**
     * 这里实现了HttpInputMessage 封装一个自己的HttpInputMessage
     * 实现rsa+ase混合解密
     */
    static class MyHttpInputMessage implements HttpInputMessage {
        HttpHeaders headers;
        InputStream body;

        public MyHttpInputMessage(HttpHeaders headers, String data) throws Exception {
            this.headers = headers;
            body = new ByteArrayInputStream(getData(data).getBytes("UTF-8"));
        }

        public MyHttpInputMessage(HttpHeaders headers, InputStream body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }

        public String getData(String requestData) throws Exception {
            if (requestData == null) {
                throw new CustomException(CommonCode.ENCRYPTION_ERROR);
            }
            JSONObject jsonObject = JSONObject.parseObject(requestData);
            String data = jsonObject.get("data").toString();
            String aesKey = jsonObject.get("aseKey").toString();
            if (data == null || aesKey == null) {
                throw new CustomException(CommonCode.ENCRYPTION_ERROR);
            }
            //后端私钥解密的到AES的key
            byte[] key = RSAUtil.decryptByPrivateKey(Base64.decodeBase64(aesKey), Base64.decodeBase64(RSAUtil.getPrivateKey()));
            //AES解密得到明文data数据
            String decrypt = AESUtil.decrypt(data, key);
            return decrypt;
        }
    }


}
