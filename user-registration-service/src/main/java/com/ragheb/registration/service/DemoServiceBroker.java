package com.ragheb.registration.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "demo-service", url = "http://localhost:9090/testService")
public interface DemoServiceBroker {

    @GetMapping("/test")
    public String test();
}
