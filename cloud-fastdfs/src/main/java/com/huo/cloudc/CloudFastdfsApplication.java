package com.huo.cloudc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CloudFastdfsApplication {
    @Value("${spring.application.name}")
    String name;

    public static void main(String[] args) {
        SpringApplication.run(CloudFastdfsApplication.class, args);
    }

}
