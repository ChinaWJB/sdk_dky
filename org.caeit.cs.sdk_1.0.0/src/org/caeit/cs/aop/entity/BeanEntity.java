package org.caeit.cs.aop.entity;

import java.util.List;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：BeanEntity.java
 * 
 * 摘 要：代表一个服务的实体类，提供类定义信息
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public class BeanEntity {
	private String name; // 服务提供者名
	private String type; // 服务提供者实例的类型
	private Object value; // 服务提供者实例
	private List<SecurityMethodEntity> securityMethodEntity; // 该Bean中所有的需要Security权限的方法集合

	public BeanEntity() {
	}

	public BeanEntity(String name, String type,
			List<SecurityMethodEntity> securityMethodEntity) {
		this.name = name;
		this.type = type;
		this.securityMethodEntity = securityMethodEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public List<SecurityMethodEntity> getSecurityMethodEntity() {
		return securityMethodEntity;
	}

	public void setSecurityMethodEntity(
			List<SecurityMethodEntity> securityMethodEntity) {
		this.securityMethodEntity = securityMethodEntity;
	}
}
