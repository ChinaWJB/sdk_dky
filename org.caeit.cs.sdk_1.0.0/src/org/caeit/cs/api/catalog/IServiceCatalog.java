package org.caeit.cs.api.catalog;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.caeit.cs.aop.annotation.Security;
import org.caeit.cs.model.dky.service.Service;
/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：IServiceCatalog.java
 * 
 * 摘 要：本类为服务目录程序的API接口类
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月26日
 * 
 * 完成日期：2015年6月26日
 * 
 */
public interface IServiceCatalog {

	@Security(name = "addservice", authority="admin")
	public abstract boolean addService(Service service);

	@Security(name = "deleteservice", authority="admin")
	public abstract boolean deleteService(Service service);

	@Security(name = "updateservice",authority="admin")
	public abstract boolean updateService(Service service);

	@Security(name = "search", authority="admin")
	public abstract List<Service> search(Map<String, String> queryTerm);

	public void doNothing();
	
	public File searchConfiguration(Class clazz);
	
//	@Security(name = "makelog", authority="admin")
	public abstract void makeConfiguration(String location);
	
}