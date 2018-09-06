package com.huo.cloud;

import com.huo.cloud.config.datasource.DataSourceEnum;
import com.huo.cloud.config.datasource.DynamicDataSourceContextHolder;
import com.huo.cloud.demo.service.DemoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudSerivceAApplicationTests {
    @Autowired
    DemoService demoService;

    @org.junit.Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                DynamicDataSourceContextHolder.setDataSource(DataSourceEnum.MASTER_DB.name);
                System.out.println("a=" + i);
            } else {
                DynamicDataSourceContextHolder.setDataSource(DataSourceEnum.OTHER_DB_1.name);
                System.out.println("a=" + i);
            }
            System.out.println(demoService.getById());
        }
    }

}
