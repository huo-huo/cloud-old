package com.huo.cloudc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CloudConfigClientApplication {
    @Value("${spring.application.name}")
    String name;

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigClientApplication.class, args);
    }

    @RequestMapping("test")
    public Object test() {
        return name;
    }
}
