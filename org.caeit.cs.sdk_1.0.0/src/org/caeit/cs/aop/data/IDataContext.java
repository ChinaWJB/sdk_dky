package org.caeit.cs.aop.data;

import java.util.Map;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：IDataContext.java
 * 
 * 摘 要：抽象的数据存储接口
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public interface IDataContext {
	
	public void initData(Map<String, Object> map);

	public void set(String name, Object obj);

	public Object get(String name);

	public Map<String, Object> getAll();
}
