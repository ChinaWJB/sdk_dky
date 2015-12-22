package org.caeit.cs.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：Bean.java
 * 
 * 摘 要：用来标注服务（提供者）的注解类
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
public @interface Bean {
	String name();
}
