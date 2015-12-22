package org.caeit.cs.aop.container;

import java.util.HashMap;
import java.util.Map;

import org.caeit.cs.aop.data.IDataContext;
import org.caeit.cs.aop.factory.AbstractBeanFactory;
import org.caeit.cs.aop.util.ReflectionUtil;
import org.caeit.cs.api.catalog.IServiceCatalog;
import org.caeit.cs.api.component.ISrvComponentList;
import org.caeit.cs.api.deploy.IServiceDeploy;
import org.caeit.cs.api.monitor.IServiceMonitor;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：CSApiContainer.java
 * 
 * 摘 要：核心服务API层默认的服务容器接口实现类,本类为单例模式，且使用工厂模式和策略模式实现容器内部具体工厂的动态加载
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月26日
 * 
 * 完成日期：2015年6月26日
 * 
 */
public class CSApiContainer implements IContainer {

	private static CSApiContainer instance;

	// 这个objectMap中的值将来可取自配置文件，因为可以加载不同的factory实现，同时定制factory的组件
	//objectMap只是用来存储与容器相关的对象
	private Map<String, Object> objectMap = new HashMap<String, Object>();
//	private Map<String, Object> objectMap = null;

	private AbstractBeanFactory factory;

	private CSApiContainer() {
		System.out.println("----------开始初始化容器---------");
		// 这个默认工厂的读取应该来自配置文件
		// 如果无自定义信息，自动加载默认的工厂实现
		System.out.println("----------开始初始化容器内工厂---------");
		factory = (AbstractBeanFactory) ReflectionUtil.
				getInstance(objectMap.get("factory") == null ? "org.caeit.cs.aop.factory.DefaultBeanFactory"
						: objectMap.get("factory").toString());
		factory.initFactory(
				objectMap.get("cacheContext") == null ? "org.caeit.cs.aop.data.DefaultBeanCacheContext"
						: objectMap.get("cacheContext").toString(),
				objectMap.get("definitionContext") == null ? "org.caeit.cs.aop.data.DefaultBeanDefinitionContext"
						: objectMap.get("definitionContext").toString(),
				objectMap.get("annotationHandler") == null ? "org.caeit.cs.aop.annotation.handler.SecurityAnnotationHandler"
						: objectMap.get("annotationHandler").toString(),
				objectMap.get("invocationHandler") == null ? "org.caeit.cs.aop.invocation.SecurityInvocationHandler"
						: objectMap.get("invocationHandler").toString());
		System.out.println("----------初始化容器内工厂成功！---------");
		System.out.println("----------初始化容器成功！---------");
	}

	public void addCoreServiceAPIBeans() {
		System.out.println("-------------开始加载服务API-----------");
		// 向容器注册相关的API Bean
//		factory.registerBeanDefinition(objectMap.get("catalog") == null ? "org.caeit.cs.api.catalog.ServiceCatalog"
//				: objectMap.get("catalog").toString());
		factory.registerBeanDefinition(objectMap.get("monitor") == null ? "org.caeit.cs.api.monitor.ServiceMonitor"
				: objectMap.get("monitor").toString());
		
//		factory.registerBeanDefinition(objectMap.get("component") == null ? "org.caeit.cs.api.component.SrvComponentList"
//				: objectMap.get("component").toString());
		// 向容器注册相关的API Bean,add by wjb,2015.11.2
//		factory.registerBeanDefinition(objectMap.get("resource") == null ? "org.caeit.cs.api.deploy.ServiceDeploy"
//				: objectMap.get("resource").toString());
		System.out.println("-------------服务API加载成功-----------");
	}

	public static CSApiContainer getInstance() {
		if (instance == null) {
			instance = new CSApiContainer();
		}
		return instance;
	}

	public static CSApiContainer getContainer() {
		getInstance();
		instance.addCoreServiceAPIBeans();
		return instance;
	}

	public IServiceCatalog getServiceCatalog() {
		return (IServiceCatalog) getBean("catalog");
	}
	
	public IServiceMonitor getServiceMonitor(){
		return (IServiceMonitor) getBean("monitor");
	}
	
//	public ISrvComponentList getComponentList(){
//		return (ISrvComponentList)getBean("component");
//	}
	
//	public IServiceDeploy getServiceDeploy() {
//		return (IServiceDeploy) getBean("deploy");
//	}

	@Override
	// 这里使用外观模式，container即是factory的外观
	public Object getBean(String name) {
		return factory.getBean(name);
	}

	@Override
	public IDataContext getBeanCacheContext() {
		return factory.getBeanCacheContext();
	}

	@Override
	public void initContainerService(String resource) {

	}
}
