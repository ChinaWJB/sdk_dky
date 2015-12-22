package org.caeit.cs.context;

import org.codehaus.jackson.JsonNode;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：ApplicationContext.java
 * 
 * 摘 要：应用程序上下文,使用单例模式，验证用户当前上下文
 * 
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月29日
 * 
 * 完成日期：2015年6月29日
 * 
 */
public class ApplicationContext {

	private static ApplicationContext instance;
	
	private ApplicationContext(){
		
	}
	
	public static ApplicationContext getInstance(){
		if(instance==null)
			instance = new ApplicationContext();
		return instance;
	}
	
	private JsonNode token;
	private JsonNode userinfo;
	
	public JsonNode getToken() {
		return token;
	}
	public void setToken(JsonNode token) {
		this.token = token;
	}
	public JsonNode getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(JsonNode userinfo) {
		this.userinfo = userinfo;
	}
	
	
}
