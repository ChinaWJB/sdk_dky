package org.caeit.cs.aop.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caeit.cs.aop.advisor.SecurityMethodAdvisor;
import org.caeit.cs.aop.annotation.Bean;
import org.caeit.cs.aop.annotation.handler.AnnotationHandlerDecorator;
import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.aop.data.IDataContext;
import org.caeit.cs.aop.entity.BeanEntity;
import org.caeit.cs.aop.entity.SecurityMethodEntity;
import org.caeit.cs.aop.invocation.SecurityInvocationHandler;
import org.caeit.cs.aop.util.ReflectionUtil;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：DefaultBeanFactory.java
 * 
 * 摘 要：实现了抽象工厂的默认工厂类
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public class DefaultBeanFactory extends AbstractBeanFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1160246578201709391L;

	@Override
	public void setAnnotationHandler(String annotationHandler) {
		System.out.println("-------------注册处理元数据的Handler-----------");
		annotationHandlerDecorator = (AnnotationHandlerDecorator) ReflectionUtil
				.getInstance(annotationHandler);
	}

	@Override
	public void setInvocationHandler(String invocationHandler) {
		System.out.println("-------------注册处理动态代理的Handler-----------");
		securityInvocationHandler = (SecurityInvocationHandler) ReflectionUtil
				.getInstance(invocationHandler);
	}

	@Override
	public void setBeanDefinitionContext(String beanDefinitionContextName) {
		System.out.println("-------------注册服务定义信息的存储区-----------");
		beanDefinitionContext = (IDataContext) ReflectionUtil
				.getInstance(beanDefinitionContextName);

	}

	@Override
	public void registerBeanDefinitionByPackage(String resource) {
		System.out.println("-------------使用Handler将元数据转换为服务定义信息-----------");
		Map<String, Object> BeanDataMap = annotationHandlerDecorator
				.convert(resource);
		System.out.println("-------------保存服务定义信息-----------");
		beanDefinitionContext.initData(BeanDataMap);
	}

	@Override
	public void registerBeanDefinition(String resource) {
		System.out.println("-------------使用Handler将元数据转换为服务定义信息-----------");
		Map<String, Object> BeanDataMap = annotationHandlerDecorator
				.convertBean(resource);
		System.out.println("-------------保存服务定义信息-----------");
		beanDefinitionContext.initData(BeanDataMap);
	}

	@Override
	public void setBeanCacheContext(String beanCacheContextName) {
		System.out.println("-------------注册服务提供者的缓存区-----------");
		beanCacheContext = (IDataContext) ReflectionUtil
				.getInstance(beanCacheContextName);
		beanCacheContext.initData(new HashMap<String, Object>());
	}

	@Override
	public BeanEntity getBeanDefinition(String name) {
		System.out.println("-------------获取" + name + "的服务定义信息-----------");
		return (BeanEntity) beanDefinitionContext.get(name);
	}

	@Override
	public boolean containsBeanCache(String name) {
		System.out.println("-------------判断服务提供者" + name + "是否有缓存-----------");
		return beanCacheContext.get(name) != null ? true : false;
	}

	@Override
	public Object getBeanCache(String name) {
		System.out.println("-------------从缓存中获取服务提供者" + name + "-----------");
		return beanCacheContext.get(name);
	}

	@Override
	// 递归调用，获取注入Bean依赖的Bean
	public Object creatBean(BeanEntity beanEntity) {
		System.out.println("-------------根据服务定义信息创建服务提供者"
				+ beanEntity.getName() + "-----------");
		// Object obj=ReflectionUtil.getInstance(beanEntity.getType());
		Object obj = null;
		try {
			obj = Class.forName(beanEntity.getType())
					.getConstructor(CSApiContainer.class)
					.newInstance(CSApiContainer.getInstance());
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		List<SecurityMethodEntity> securityMethodList = beanEntity
				.getSecurityMethodEntity();
		if (securityMethodList != null) {
			SecurityMethodAdvisor advisor = new SecurityMethodAdvisor();
			securityInvocationHandler.setSecurityAdvisorAdvisor(advisor);
		}

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			Bean bean = field.getAnnotation(Bean.class);
			if (bean == null) {
				continue;
			}
			field.setAccessible(true);
			try {
				// 递归调用
				field.set(obj, getBean(bean.name()));
			} catch (Exception e) {

			}
		}

		// 返回该对象的动态代理对象
		return securityInvocationHandler.setObject(obj);
	}

	@Override
	public Object regiterBeanCache(String name, Object obj) {
		System.out.println("-------------将服务提供者" + name + "放入缓存区-----------");
		beanCacheContext.set(name, obj);
		return null;
	}

	@Override
	public IDataContext getBeanDefinitionContext() {
		return beanDefinitionContext;
	}

	@Override
	public IDataContext getBeanCacheContext() {
		return beanCacheContext;
	}

}
