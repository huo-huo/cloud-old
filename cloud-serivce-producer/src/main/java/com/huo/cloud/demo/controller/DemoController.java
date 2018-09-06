package com.huo.cloud.demo.controller;

import com.huo.cloud.config.datasource.DataSourceEnum;
import com.huo.cloud.config.datasource.DynamicDataSourceContextHolder;
import com.huo.cloud.demo.po.Demo;
import com.huo.cloud.demo.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    static int i = 0;


    @Value("${server.port}")
    String port;

    @Autowired
    DemoService demoService;

    @RequestMapping("demo")
    public Object demo( String name) {
        if (i % 2 == 0) {
            DynamicDataSourceContextHolder.setDataSource(DataSourceEnum.MASTER_DB.name);
            System.out.println("a=" + i);
        } else {
            DynamicDataSourceContextHolder.setDataSource(DataSourceEnum.OTHER_DB_1.name);
            System.out.println("a=" + i);
        }
        i++;
        Demo demo = demoService.getById();
        return "service-a port:" + port + " data: " + demo + "param:" + name;
    }

}
