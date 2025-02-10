package com.ragheb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testService")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Hello From Demo application!";
    }
}
