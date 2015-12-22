package org.caeit.cs.aop.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：DefaultBeanDefinitionContext.java
 * 
 * 摘 要：默认的服务描述信息存储上下文环境
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年4月22日
 * 
 * 完成日期：2015年4月22日
 * 
 */
public class DefaultBeanDefinitionContext implements IDataContext {

	private Map<String, Object> beanDefinitionMap = new HashMap<String, Object>();

	public DefaultBeanDefinitionContext() {
	}

	@Override
	public void initData(Map<String, Object> map) {
		for (String key : map.keySet()) {
			beanDefinitionMap.put(key, map.get(key));
		}
	}

	@Override
	public void set(String name, Object obj) {
		beanDefinitionMap.put(name, obj);
	}

	@Override
	public Object get(String name) {
		return beanDefinitionMap.get(name);
	}

	@Override
	public Map<String, Object> getAll() {
		return beanDefinitionMap;
	}
}