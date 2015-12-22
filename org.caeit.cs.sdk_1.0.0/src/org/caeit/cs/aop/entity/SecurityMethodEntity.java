package org.caeit.cs.aop.entity;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：SecurityMethodEntity.java
 * 
 * 摘 要：api实体类所提供的安全认证方法的定义信息类
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月23日
 * 
 * 完成日期：2015年6月23日
 * 
 */
public class SecurityMethodEntity {
	private String name; // 服务名
	private String value; // 服务对应的方法名

	public SecurityMethodEntity() {
	}

	public SecurityMethodEntity(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
