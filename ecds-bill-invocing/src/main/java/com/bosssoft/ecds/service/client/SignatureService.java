package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.service.fallback.FallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "ecds-template1", fallback = FallBack.class)
public interface SignatureService {
    /**
     * 单位端电子签名
     * @return
     */
    @RequestMapping("/")
    String sign(@RequestParam("dto") String dto);

    /**
     *验证签名和盖章接口
     * @param multipartFile
     * @param code
     * @param verifyCode
     * @return
     */
    @RequestMapping(value = "/faceApi/imageToByte", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String verifyAndSign(@RequestPart("file")MultipartFile multipartFile, @RequestParam("code")String code,
                         @RequestParam("verifyCode") String verifyCode);

}
