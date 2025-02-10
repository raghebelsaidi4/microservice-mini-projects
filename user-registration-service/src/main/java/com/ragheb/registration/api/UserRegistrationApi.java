package com.ragheb.registration.api;

import com.ragheb.registration.dto.UserDTO;
import com.ragheb.registration.service.DemoServiceBroker;
import com.ragheb.registration.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRegistrationApi {

    //private final EmailKeyDTO emailKeyDTO;
    private final UserRegistrationService service;
    private final DemoServiceBroker broker;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        this.service.register(userDTO);
        return new ResponseEntity<>("User has been registered", HttpStatus.CREATED);
    }

//    @GetMapping("/email")
//    public EmailKeyDTO getEmailConfigProperties(){
//        return emailKeyDTO;
//    }
    @GetMapping("/demo")
    public String callDemoService(){
        return this.broker.test();
    }
}
