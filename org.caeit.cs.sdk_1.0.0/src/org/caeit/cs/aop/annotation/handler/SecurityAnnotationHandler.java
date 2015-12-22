package org.caeit.cs.aop.annotation.handler;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caeit.cs.aop.annotation.Bean;
import org.caeit.cs.aop.annotation.Security;
import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.aop.entity.BeanEntity;
import org.caeit.cs.aop.entity.SecurityMethodEntity;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：DefaultAnnotationHandler.java
 * 
 * 摘 要：安全认证元数据处理器装饰对象的具体实现（处理注解）
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月22日
 * 
 * 完成日期：2015年6月22日
 * 
 */
public class SecurityAnnotationHandler extends AnnotationHandlerDecorator {

	/**
	 * @param packName
	 *            根据包名来获取此包下所有的类名及其实例
	 * 
	 */
	@Override
	public Map<String, Object> convert(String packName) {
		// System.out.println("这是一个Annotation的handler");
		Map<String, Object> map = super.convert(packName);

		Map<String, Object> beanMap = new HashMap<String, Object>();
		String packageName = packName;
		String packageDirName = packageName.replace(".", "/");
		Enumeration<URL> dirs = null;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);

			// 迭代此 Enumeration
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				File file = new File(url.getFile());
				// 把此目录下的所有文件列出
				String[] classes = file.list();
				// 循环此数组，并把.class去掉
				for (String className : classes) {
					className = className.substring(0, className.length() - 6);
					// 拼接上包名，变成全限定名
					String qName = packageName + "." + className;
					// 如有需要，把每个类生实一个实例
					Object obj = Class.forName(qName).getConstructor(CSApiContainer.class).newInstance(CSApiContainer.getInstance());

					// 如果这个类有@Bean注解，则将其返回。
					boolean flag = obj.getClass().isAnnotationPresent(
							Bean.class);

					// ----------------处理类注解-----begin-----------------
					if (flag) {
						Bean bean = (Bean) obj.getClass().getAnnotation(Bean.class);
						// System.out.println("描述bean的名称:"+bean.name());
						// System.out.println("描述bean的类型:"+qName);

						Method[] methods = obj.getClass().getMethods();
						List<SecurityMethodEntity> securityMethodList = new ArrayList<SecurityMethodEntity>();

						// ------------处理方法注解----begin------------
						for (Method method : methods) {
							if (method.getAnnotation(Security.class) == null) {
								continue;
							}
							// System.out.println("方法名称:" + method.getName());
							Security securityMethod = (Security) method
									.getAnnotation(Security.class);
							securityMethodList.add(new SecurityMethodEntity(
									securityMethod.name(), method.getName()));
						}
						// ------------处理方法注解----end------------
						beanMap.put(bean.name(), new BeanEntity(bean.name(),
								qName, securityMethodList));
					}

					// ----------------处理类注解-----end-----------------

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (map != null && map.size() != 0) {
			beanMap.putAll(map);
		}

		return beanMap;

	}
	
	/**
	 * @param packName
	 *            根据全限定的类名来加载相应的类定义信息
	 * 
	 */
	@Override
	public Map<String, Object> convertBean(String qName) {
		Map<String, Object> beanMap = new HashMap<String, Object>();
		try {
			// 如有需要，把每个类生实一个实例
			Object obj = Class.forName(qName).getConstructor(CSApiContainer.class).newInstance(CSApiContainer.getInstance());

			// 如果这个类有@Bean注解，则将其返回。
			boolean flag = obj.getClass().isAnnotationPresent(Bean.class);

			// ----------------处理类注解-----begin-----------------
			if (flag) {
				Bean bean = (Bean) obj.getClass().getAnnotation(Bean.class);
				// System.out.println("描述bean的名称:"+bean.name());
				// System.out.println("描述bean的类型:"+qName);

				Method[] methods = obj.getClass().getMethods();
				List<SecurityMethodEntity> securityMethodList = new ArrayList<SecurityMethodEntity>();

				// ------------处理方法注解----begin------------
				for (Method method : methods) {
					if (method.getAnnotation(Security.class) == null) {
						continue;
					}
					// System.out.println("方法名称:" + method.getName());
					Security securityMethod = (Security) method
							.getAnnotation(Security.class);
					securityMethodList.add(new SecurityMethodEntity(
							securityMethod.name(), method.getName()));
				}
				// ------------处理方法注解----end------------
				beanMap.put(bean.name(), new BeanEntity(bean.name(),
						qName, securityMethodList));
			}

			// ----------------处理类注解-----end-----------------
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanMap;
	}
}
