package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.entity.dto.SignedDataDto;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import feign.Response;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import io.netty.handler.codec.DecoderException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;


@FeignClient(value = "unit-sign", configuration = SignatureService.MultipartSupportConfig.class)
public interface SignatureService {
    /**
     * 单位端电子签名
     * @return
     */
    @PostMapping("/sign")
    Object sign(@RequestParam("data") String data);

    /**
     * 签名验证
     * @param signedData
     * @return
     */
    @PostMapping("/verifySign")
    ResponseResult verifySign(@RequestBody SignedDataDto signedData) throws NoSuchProviderException,
            CertificateException, NoSuchAlgorithmException,
            InvalidKeyException, SignatureException, DecoderException;

    /**
     *盖章接口
     * @param financeSignValue
     * @param uploadFile
     * @param unitSignValue
     * @return
     */
    @PostMapping(path = "/stamp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response stamp(@RequestPart("uploadFile") MultipartFile uploadFile,
                          @RequestParam("unitSignValue") String unitSignValue,
                          @RequestParam("financeSignValue")String financeSignValue) throws Exception;

    class MultipartSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }
}
