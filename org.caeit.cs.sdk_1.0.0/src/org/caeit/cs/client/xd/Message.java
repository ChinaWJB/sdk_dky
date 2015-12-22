package org.caeit.cs.client.xd;

import java.io.Serializable;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：Message.java
 * 
 * 摘 要：与西电程序进行交互时使用的消息类型
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月24日
 * 
 * 完成日期：2015年6月24日
 * 
 */
public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5949170943524546850L;
	private MsgType type;
	private Object body;
	public Message(MsgType type, Object body)
	{
		this.type = type;
		this.body = body;
	}
	
	public MsgType getType()
	{
		return type;
	}
	public void setType(MsgType type)
	{
		this.type = type;
	}
	public Object getBody()
	{
		return body;
	}
	public void setBody(Object obj)
	{
		body = obj;
	}
}