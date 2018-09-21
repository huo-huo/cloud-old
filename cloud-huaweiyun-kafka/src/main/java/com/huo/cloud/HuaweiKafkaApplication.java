package com.huo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HuaweiKafkaApplication {

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "classpath:kafka_client_jaas.conf");
        SpringApplication.run(HuaweiKafkaApplication.class, args);
    }

}
