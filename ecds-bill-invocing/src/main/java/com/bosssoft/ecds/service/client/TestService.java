package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.service.fallback.FallBack;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@FeignClient(value = "bill-destroy", fallback = FallBack.class)
public interface TestService {

    @GetMapping(value = "/test")
    String test(@RequestParam("test") String test) throws ExecutionException, InterruptedException;
}
