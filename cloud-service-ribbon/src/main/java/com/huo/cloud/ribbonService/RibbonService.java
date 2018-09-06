package com.huo.cloud.ribbonService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;


    public String demoError(String name) {
        return "ribbon err";
    }

    @HystrixCommand(fallbackMethod = "demoError")
    public String demo(String name) {
        return restTemplate.getForObject("http://service-producer/demo?name=" + name, String.class);
    }

}