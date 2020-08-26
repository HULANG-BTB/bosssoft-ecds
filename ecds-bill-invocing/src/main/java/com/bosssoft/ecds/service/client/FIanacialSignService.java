package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.entity.dto.SignedDataDto;
import com.bosssoft.ecds.response.QueryResponseResult;
import feign.Response;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "financial-sign")
public interface FIanacialSignService {
    /**
     * 财政端签名
     * @param signedData
     * @return
     * @throws Exception
     */
    @PostMapping("/sign")
    QueryResponseResult sign(@RequestBody SignedDataDto signedData)throws Exception;

}
