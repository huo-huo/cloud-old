package com.huo.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.**.dao")
public class CloudServiceProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceProducerApplication.class, args);
    }
}
