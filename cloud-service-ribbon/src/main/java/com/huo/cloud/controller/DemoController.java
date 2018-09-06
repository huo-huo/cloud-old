package com.huo.cloud.controller;

import com.huo.cloud.ribbonService.RibbonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Value("${server.port}")
    String port;

    @Autowired
    RibbonService ribbonService;

    @RequestMapping(value = "/demo")
    public String sayHi(String name) {
        return "ribbon :" + port + " => " + ribbonService.demo(name);
    }
}
