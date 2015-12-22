package org.caeit.cs.aop.factory;

import java.io.Serializable;

import org.caeit.cs.aop.annotation.handler.AnnotationHandlerDecorator;
import org.caeit.cs.aop.data.IDataContext;
import org.caeit.cs.aop.entity.BeanEntity;
import org.caeit.cs.aop.invocation.SecurityInvocationHandler;
/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：AbstractBeanFactory.java
 * 
 * 摘 要：抽象工厂类，本类只实现了 初始化工厂 initFactory() 和 获取服务对象 getBean() 两个模板方法
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public abstract class AbstractBeanFactory implements IBeanFactory, Serializable {

	private static final long serialVersionUID = -3389886016848349634L;

	// ----------组件初始化----------begin-----
	protected IDataContext beanDefinitionContext;// 服务描述信息的存储区
	protected IDataContext beanCacheContext; // 服务提供者实例的缓存区
	protected AnnotationHandlerDecorator annotationHandlerDecorator;// 转换器（元数据到服务描述信息）
	protected SecurityInvocationHandler securityInvocationHandler;// 调用处理器

	// 设置服务描述信息的存储区
	public abstract void setBeanDefinitionContext(
			String beanDefinitionContextName);

	// 设置服务提供者实例的缓存区
	public abstract void setBeanCacheContext(String beanCacheContextName);

	// 设置 注解解析器 和 调用解析器
	public abstract void setAnnotationHandler(String annotationHandler);

	public abstract void setInvocationHandler(String invocationHandler);

	@Override
	// 模板方法
	// 注册服务组件，初始化服务描述信息
	public final void initFactory(String cacheContext,
			String definitionContext, String annotationHandler,
			String invocationHandler) {
		this.setAnnotationHandler(annotationHandler);
		this.setInvocationHandler(invocationHandler);
		this.setBeanCacheContext(cacheContext);
		this.setBeanDefinitionContext(definitionContext);
	}
	
	public abstract void registerBeanDefinition(String resource);

	// ----------组件初始化----------end-----

	// ----------服务提供者的生命周期-----begin--------

	// 获取某个服务提供者的服务描述信息
	public abstract BeanEntity getBeanDefinition(String name);

	// 检查该服务提供者的实例是否有缓存
	public abstract boolean containsBeanCache(String name);

	// 从缓存中获取服务提供者的实例
	public abstract Object getBeanCache(String name);

	// 创建服务提供者
	public abstract Object creatBean(BeanEntity beanEntity);

	// 将服务提供者实例注册到缓存
	public abstract Object regiterBeanCache(String name, Object obj);

	// ----------服务提供者的生命周期-----end--------

	@Override
	// 模板方法
	// 获取服务提供者实例
	public final Object getBean(String name) {

		// 获取某个Bean的定义
		BeanEntity beanEntity = getBeanDefinition(name);
		// 判断这个Bean是否已经加载到缓存，如果有，直接返回
		if (containsBeanCache(name)) {
			return getBeanCache(name);
		}
		// 创建bean的实例,实际上这里的beanObject已经是经过工厂时生成的动态代理对象了
		Object beanObject = this.creatBean(beanEntity);

		// 注册到缓存
		regiterBeanCache(name, beanObject);

		// 返回bean的实例
		return beanObject;
	}

	// 获取所有的服务描述信息
	public abstract IDataContext getBeanDefinitionContext();

	// 获取所有的服务实例缓存信息
	public abstract IDataContext getBeanCacheContext();

}
