package com.huo.cloud.demo.po;

import java.util.Date;

import lombok.Data;

@Data
public class Demo {
    private Integer id;

    private String name;

    private String info;

    private String addr;

    private Date birth;

}