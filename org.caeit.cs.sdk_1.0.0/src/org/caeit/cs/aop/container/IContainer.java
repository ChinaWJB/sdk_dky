package org.caeit.cs.aop.container;

import org.caeit.cs.aop.data.IDataContext;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：IContainer.java
 * 
 * 摘 要：服务容器接口
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月16日
 * 
 * 完成日期：2015年6月16日
 * 
 */
public interface IContainer {
	/**
	 * 获取容器的某个服务提供者实例
	 * @param name
	 * @return
	 */
	public Object getBean(String name);
	
	 
	 /**
	  * 获取容器中所有服务提供者实例的缓存
	  * @return
	  */
	 public IDataContext getBeanCacheContext();
	 
	 
	 /**
	  * 热加载新的服务提供者
	  * @param resource
	  */
	 public void initContainerService(String resource);
}
