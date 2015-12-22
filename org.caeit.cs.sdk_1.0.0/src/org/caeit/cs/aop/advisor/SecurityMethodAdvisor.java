package org.caeit.cs.aop.advisor;

import java.lang.reflect.Method;

import org.caeit.cs.aop.annotation.Security;
import org.caeit.cs.api.IOAuthClient;
import org.caeit.cs.api.security.OAuthAgent;
import org.caeit.cs.context.ApplicationContext;
import org.caeit.cs.view.LoginDialog;
import org.codehaus.jackson.JsonNode;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：SecurityMethodAdvisor.java
 * 
 * 摘 要：安全SecurityMethodAdvisor 的实现
 * 
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月23日
 * 
 * 完成日期：2015年6月23日
 * 
 */
public class SecurityMethodAdvisor implements Advisor {

	@Override
	public void doInAdvisor(Object proxy, Method method, Object[] args) {
		// System.out.println("弹出登录对话框");
		// 1、首先检查当前的ApplicationContext中是否存在相关用户信息（如果存在用户信息，证明用户登录成功，用户名和密码没有问题）
		ApplicationContext context = ApplicationContext.getInstance();
		// 2、如果不存在，则弹出用户登录对话框，取得当前用户的用户名和密码，去验证中心，验证用户输入的用户名和密码的正确性
		if (context.getToken() == null && context.getUserinfo() == null) {
			showLoginDialog(((IOAuthClient) proxy).getClientId(), method);
		}

		// 3、如果存在，使用当前用户信息，来验证当前用户是否具有本客户端API的使用权限，如果有则继续执行，如果没有则报出安全异常

	}

	/**
	 * 弹出登录对话框，根据用户登录使用的相应的信息取验证用户
	 * 
	 * @param clientId
	 */
	public void showLoginDialog(final String clientId, final Method method) {
		Thread dialogThread = new Thread(new Runnable() {
			public void run() {
				LoginDialog dialog = new LoginDialog(new javax.swing.JFrame(),true) {
					private static final long serialVersionUID = 1269804360317936360L;

					/**
					 * 重载对话框的中点击登录方法后应该实现的事件,检查用户合法性
					 */
					@Override
					public void doLoginFunction(String username, String password) {
						OAuthAgent oauthAgent = new OAuthAgent();
						JsonNode userInfo = oauthAgent.getUserInfo(clientId,
								username, password);
						// 如果用户合法，则放开当前线程的执行
						if (userInfo != null) {
							ApplicationContext context = ApplicationContext
									.getInstance();
							context.setUserinfo(userInfo);
							// 释放所有等待此线程的线程继续执行
							synchronized (this) {
								notify();
							}
						} else {
							// do nothing
						}
					}
				};
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});

		try {
			synchronized (dialogThread) {
				dialogThread.start();
				dialogThread.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
