package org.caeit.cs.aop.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：DefaultBeanDefinitionContext.java
 * 
 * 摘 要：默认的服务实例存储缓存上下文环境
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public class DefaultBeanCacheContext implements IDataContext {

	private Map<String, Object> beanCacheMap = new HashMap<String, Object>();

	@Override
	public void initData(Map<String, Object> map) {
		for(String key : map.keySet()){
			beanCacheMap.put(key, map.get(key));
		}
	}

	@Override
	public void set(String name, Object obj) {
		beanCacheMap.put(name, obj);
	}

	@Override
	public Object get(String name) {
		return beanCacheMap.get(name);
	}

	@Override
	public Map<String, Object> getAll() {
		return beanCacheMap;
	}
}
