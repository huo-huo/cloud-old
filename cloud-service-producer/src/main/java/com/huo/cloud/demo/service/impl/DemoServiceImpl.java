package com.huo.cloud.demo.service.impl;

import com.huo.cloud.demo.dao.DemoMapper;
import com.huo.cloud.demo.po.Demo;
import com.huo.cloud.demo.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoMapper;

	@Override
	public Demo getById() {
		return demoMapper.selectByPrimaryKey(1);
	}

}
