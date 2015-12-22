package org.caeit.cs.aop.annotation.handler;

import java.util.Map;


/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：HandlerDecorator.java
 * 
 * 摘 要：抽象的处理器装饰者类
 * 
 *  使用装饰者模式，可以动态添加功能（这里是动态添加元数据处理器）,例如要扩展Annotation就需要自己扩展处理器，或者扩展xml处理器等等
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public class AnnotationHandlerDecorator implements IAnnotationHandler {
	protected IAnnotationHandler handler;

	public void setHandler(IAnnotationHandler handler) {
		this.handler = handler;
	}

	@Override
	public Map<String, Object> convert(String string) {
		if (handler != null) {
			return handler.convert(string);
		}
		return null;
	}

	@Override
	public Map<String, Object> convertBean(String qname) {
		if (handler != null) {
			return handler.convertBean(qname);
		}
		return null;
	}
}
