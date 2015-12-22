package org.caeit.cs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：CoreServiceProperties.java
 * 
 * 摘 要：用来读取properties配置文件的类
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月24日
 * 
 * 完成日期：2015年6月24日
 * 
 */
public class CoreServiceProperties {
	
	static Logger logger = Logger.getLogger(CoreServiceProperties.class);
	
	//服务注册中心地址，包括服务审核
	public static String serviceRegisterIP;
	//服务注册中心端口号
	public static String serviceRegisterPort;
	
	//安全认证地址
	public static String safetyCertificationIP;
	//安全认证端口号
	public static String safetyCertificationPort;
	
	//服务目录地址
	public static String serviceCatalogIP;
	//服务目录端口号
	public static String serviceCatalogPort;
	
	//服务管理地址
	public static String serviceManagementIP;
	//服务管理端口号
	public static String serviceManagementPort;
	
	//服务监控地址
	public static String serviceMonitorIP;
	//服务监控端口号
	public static String serviceMonitorPort;
	
	//流程管理地址
	public static String flowManagementIP;
	//流程管理端口号
	public static String flowManagementPort;
	
	static {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(Thread.currentThread()
					.getContextClassLoader().getResource("core_service.properties")
					.getFile()));
			serviceRegisterIP = p.getProperty("register.ip");
			serviceRegisterPort = p.getProperty("register.port");
			
			safetyCertificationIP = p.getProperty("safety.ip");
			safetyCertificationPort = p.getProperty("safety.port");
			
			serviceCatalogIP = p.getProperty("catalog.ip");
			serviceCatalogPort = p.getProperty("catalog.port");
			
			serviceManagementIP = p.getProperty("manage.ip");
			serviceManagementPort = p.getProperty("manage.port");
			
			serviceMonitorIP = p.getProperty("monitor.ip");
			serviceMonitorPort = p.getProperty("monitor.Port");
			
			flowManagementIP = p.getProperty("flow.ip");
			flowManagementPort = p.getProperty("flow.port");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
