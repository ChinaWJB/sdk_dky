package org.caeit.cs.aop.factory;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：IBeanFactory.java
 * 
 * 摘 要：容器的核心工厂接口
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public interface IBeanFactory {
	/**
	 * 根据服务提供者的名字获取服务提供者的实例
	 * 
	 * @param name
	 *            服务提供者的名字
	 * @return 服务提供者的实例
	 */
	public Object getBean(String name);

	/**
	 * 根据包名来初始化该包下的所有的类定义信息
	 * 
	 * @param resource
	 *            默认元数据的位置（实质是一个包的路径），可以自定义
	 */
	public void registerBeanDefinitionByPackage(String resource);

	/**
	 * 根据全限定的类名，加载该类定义信息
	 * 
	 * @param resource
	 */
	public void registerBeanDefinition(String resource);

	/**
	 * 初始化工厂
	 * 
	 * @param cacheContext
	 *            默认服务提供者实例的缓存区 （缓存），可以自定义
	 * @param definitionContext
	 *            默认服务描述信息的存储区（类似Jndi），可以自定义
	 * @param annotationHandler
	 *            默认注解处理器，负责将《元数据》转换为《服务描述信息》, 不能被替换，可以再其前累加自定义处理器
	 * @param invocationHandler
	 *            默认调用处理器，负责处理注解为“@Security”形式的注解方法的调用前检查
	 */
	public void initFactory(String cacheContext, String definitionContext,
			String annotationHandler, String invocationHandler);
}
