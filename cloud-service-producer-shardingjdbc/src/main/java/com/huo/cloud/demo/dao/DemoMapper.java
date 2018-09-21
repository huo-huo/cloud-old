package com.huo.cloud.demo.dao;

import com.huo.cloud.demo.po.Demo;

public interface DemoMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Demo record);

    int insertSelective(Demo record);

    Demo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
}