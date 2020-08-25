package com.bosssoft.ecds.system.service.client;

import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.system.entity.vo.SendMailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lwn
 * @create 2020/8/14 13:58
 */
@FeignClient(value = "boss-ecds-bill-message")
public interface MailClient {

    /**
     * 调用发送邮件接口
     *
     * @return 结果
     */
    @GetMapping("/mail/send")
    ResponseResult sendMail(@RequestBody SendMailVo sendMailVo);

}