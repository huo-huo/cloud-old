package com.huo.cloud.controller;

import com.huo.cloud.feign.SchedualServiceA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    SchedualServiceA schedualServiceA;
    @RequestMapping(value = "/demo")
    public String sayHi(String name){
        return schedualServiceA.sayHiFromClientOne(name);
    }
}
