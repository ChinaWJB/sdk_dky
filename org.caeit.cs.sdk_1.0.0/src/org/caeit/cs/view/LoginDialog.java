/*
 * LoginDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package org.caeit.cs.view;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：LoginDialog.java
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
public abstract class LoginDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7100586414244905583L;
	
	/** Creates new form LoginDialog */
	public LoginDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnLogin = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		txtUsername = new javax.swing.JTextField();
		txtPassword = new javax.swing.JPasswordField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u7528\u6237\u767b\u5f55");
		setBackground(java.awt.Color.white);
		setBounds(new java.awt.Rectangle(760, 380, 420, 280));
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setName("loginDialog");

		btnLogin.setText("\u767b  \u5f55");
		btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnLoginMouseClicked(evt);
			}
		});

		btnCancel.setText("\u53d6  \u6d88");
		btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnCancelMouseClicked(evt);
			}
		});

		jLabel1.setText("\u7528\u6237\u540d:");

		jLabel2.setText("\u5bc6\u7801:");

		txtUsername.setToolTipText("\u7528\u6237\u540d");

		txtPassword.setToolTipText("\u5bc6\u7801");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGap(62, 62,
																		62)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						jLabel1))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						txtPassword,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						227,
																						Short.MAX_VALUE)
																				.addComponent(
																						txtUsername,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						227,
																						Short.MAX_VALUE)))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap(
																		198,
																		Short.MAX_VALUE)
																.addComponent(
																		btnLogin)
																.addGap(18, 18,
																		18)
																.addComponent(
																		btnCancel)))
								.addGap(54, 54, 54)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(64, 64, 64)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														txtUsername,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2)
												.addComponent(
														txtPassword,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										37, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnCancel)
												.addComponent(btnLogin))
								.addGap(39, 39, 39)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		System.exit(0);
	}

	/**
	 * 登录对话框内容
	 * @param evt
	 */
	private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		System.out.println("用户名："+txtUsername.getText().trim());
		System.out.println("密码："+new String(txtPassword.getPassword()));
		String username = txtUsername.getText().trim();
		String password = new String(txtPassword.getPassword());
		doLoginFunction(username, password);
	}
	
	/**
	 * 真正处理登录业务功能的方法，该方法由调用本对话框的类来具体实现
	 * @param username
	 * @param password
	 */
	public abstract void doLoginFunction(String username, String password);

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnLogin;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtUsername;
	// End of variables declaration//GEN-END:variables

}