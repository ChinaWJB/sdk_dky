package org.caeit.cs.aop.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.caeit.cs.aop.advisor.Advisor;
import org.caeit.cs.aop.annotation.Security;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：SecurityInvocationHandler.java
 * 
 * 摘 要：实现 安全认证拦截器 功能的动态代理实现类，经过该动态代理处理之后，对原有类的调用都会被拦截，并且根据方法中有无@Security声明，决定是否进行安全认证
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public class SecurityInvocationHandler implements InvocationHandler {
	private Object target;
	Advisor securityAdvisor;

	/**
	 * 处理target对象，返回经过动态代理包装过的代理对象
	 * @param target
	 * @return
	 */
	public Object setObject(Object target) {
		this.target = target;
		Object proxy = Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(), this);
		return proxy;
	}

	/**
	 * 判断相关方法是否是AOP的方法，并对相关方法进行调用
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if (securityAdvisor != null
				&& (method.getAnnotation(Security.class) != null)) {
			securityAdvisor.doInAdvisor(proxy, method, args);
		}
		Object o = method.invoke(target, args);
		return o;
	}

	/**
	 * 设定处理安全认证的对象
	 * @param securityAdvisor
	 */
	public void setSecurityAdvisorAdvisor(Advisor securityAdvisor) {
		this.securityAdvisor = securityAdvisor;
	}

}