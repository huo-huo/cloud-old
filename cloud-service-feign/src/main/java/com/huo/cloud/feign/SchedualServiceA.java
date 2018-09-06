package com.huo.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-a",fallback = SchedualServiceAHystric.class)
public interface SchedualServiceA {

    @RequestMapping(value = "/demo")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
