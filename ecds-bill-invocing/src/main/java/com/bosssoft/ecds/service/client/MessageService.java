package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.entity.vo.SendMailVo;
import com.bosssoft.ecds.entity.vo.SendSmsVo;
import com.bosssoft.ecds.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@FeignClient(value = "boss-ecds-bill-message")
public interface MessageService {

    /**
     * 邮件发送
     * @return
     */
    @PostMapping("/mail/send")
    ResponseResult sendMail(@RequestBody SendMailVo sendMailVo) throws ExecutionException, InterruptedException;

    /**
     * 短信发送给
     * @return
     */
    @PostMapping("/sms/send")
    ResponseResult send(@RequestBody SendSmsVo sendSmsVo)  throws ExecutionException, InterruptedException;

    @GetMapping("/test")
    void test(@RequestParam("value")String value);
}
