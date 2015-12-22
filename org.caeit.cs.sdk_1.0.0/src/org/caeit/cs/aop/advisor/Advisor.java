package org.caeit.cs.aop.advisor;

import java.lang.reflect.Method;
/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：Advisor.java
 * 
 * 摘 要：Advisor的抽象接口
 * 
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月23日
 * 
 * 完成日期：2015年6月23日
 * 
 */
public interface Advisor {
	public void doInAdvisor(Object proxy, Method method, Object[] args);
}