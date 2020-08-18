package com.bosssoft.ecds.encryption;


import com.alibaba.fastjson.JSONObject;
import com.bosssoft.ecds.util.AESUtil;
import com.bosssoft.ecds.util.AESUtils;
import com.bosssoft.ecds.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
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
        return methodParameter.hasMethodAnnotation(SecretAnnotation.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            //这个request其实就是入参 可以从这里获取流
            //入参放在HttpInputMessage里面  这个方法的返回值也是HttpInputMessage
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
        /****   解密     */
        boolean decode = false;
        if (parameter.getMethod().isAnnotationPresent(SecretAnnotation.class)) {
            //获取注解配置的包含和去除字段
            SecretAnnotation serializedField = parameter.getMethodAnnotation(SecretAnnotation.class);
            //出参是否需要加密
            decode = serializedField.decode();
        }
        if (decode) {
            //获取请求数据
            String builderString = stringBuilder.toString();
            try {
                JSONObject jsonObject = JSONObject.parseObject(builderString);
                String data = jsonObject.get("data").toString();
                String publicKey = jsonObject.get("publicKey").toString();
//                String aesKey = jsonObject.get("aseKey").toString();
                String decodeString = AESUtils.decrypt(data);
                String aesKey = "";

                //后端私钥解密的到AES的key
                byte[] plaintext = RSAUtil.decryptByPrivateKey(Base64.decodeBase64(aesKey), RSAUtil.getPrivateKey());
                aesKey = new String(plaintext);
                log.info("解密出来的AES的key：" + aesKey);
                //RSA解密出来字符串多一对双引号
                aesKey = aesKey.substring(1, aesKey.length() - 1);
                //AES解密得到明文data数据
                String decrypt = AESUtil.decrypt(data, aesKey);

                //把数据放到我们封装的对象中
                return new MyHttpInputMessage(inputMessage.getHeaders(), new ByteArrayInputStream(decodeString.getBytes("UTF-8")));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;


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

    //这里实现了HttpInputMessage 封装一个自己的HttpInputMessage
    static class MyHttpInputMessage implements HttpInputMessage {
        HttpHeaders headers;
        InputStream body;

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
    }


}
