package org.caeit.cs.aop.annotation.handler;

import java.util.Map;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：IBeanFactory.java
 * 
 * 摘 要：装饰者模式的对象接口，负责相应的资源，将其转化为服务描述信息，容器启动时，用这些服务描述信息初始化服务
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public interface IAnnotationHandler {
	/**
	 * 扫描指定目录下的资源(类文件)，将其转化为服务描述信息，容器启动时，用这些服务描述信息初始化服务
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Object> convert(String string);
	
	public Map<String, Object> convertBean(String qname);

}
