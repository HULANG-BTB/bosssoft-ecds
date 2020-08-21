package com.bosssoft.ecds.fallback;

import com.bosssoft.ecds.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description : 处理
 * @Author : wuliming
 * @Date: 2020-08-17 17:26
 */

@RestController
public class FallbackController {

    @Autowired
    FallbackService fallbackService;

    /**
     * 处理Hystrix 调用异常
     * @param exchange
     * @param throwable
     * @return
     */
    @RequestMapping(value = "/fallback")
    @ResponseStatus
    public Mono<ResponseResult> fallback(ServerWebExchange exchange, Throwable throwable) {
          return  Mono.just(fallbackService.manageFallBack(exchange,throwable));
    }
}
