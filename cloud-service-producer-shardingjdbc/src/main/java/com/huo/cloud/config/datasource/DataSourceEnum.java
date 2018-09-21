package com.huo.cloud.config.datasource;

public enum DataSourceEnum {
	MASTER_DB(1,"masterDB"),
	OTHER_DB_1(2,"otherDB1"),
	SHARDING(3,"sharding");
	public Integer key;
	public String name;
	DataSourceEnum(Integer key,String name){
		this.key = key;
		this.name = name;
	}

}
