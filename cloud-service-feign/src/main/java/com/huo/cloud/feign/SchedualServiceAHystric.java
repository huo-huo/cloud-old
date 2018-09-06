package com.huo.cloud.feign;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceAHystric implements SchedualServiceA {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
