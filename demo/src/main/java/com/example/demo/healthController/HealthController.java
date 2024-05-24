package com.example.demo.healthController;

import com.example.demo.dto.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator")
public class HealthController {

    @GetMapping("/test")
    public Message testEndpoint(){
        return new Message("actuator testing UP!!");
    }
}
