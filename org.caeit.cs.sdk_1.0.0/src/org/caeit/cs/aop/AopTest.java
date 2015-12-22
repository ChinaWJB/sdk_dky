package org.caeit.cs.aop;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.catalog.IServiceCatalog;
import org.caeit.cs.api.component.ISrvComponentList;
import org.caeit.cs.api.component.SrvComponentList;
import org.caeit.cs.api.deploy.IServiceDeploy;
import org.caeit.cs.api.monitor.IServiceMonitor;
import org.caeit.cs.model.dky.service.Service;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wjb.test.Testlog4j;
/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：AopTest.java
 * 
 * 摘 要：AOP框架测试程序
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月23日
 * 
 * 完成日期：2015年6月23日
 * 
 */
public class AopTest {
	public static void main(String[] args) {
		CSApiContainer container = CSApiContainer.getContainer();
//		IServiceCatalog catalog = container.getServiceCatalog();
		IServiceMonitor monitor = container.getServiceMonitor();
//		ISrvComponentList component = container.getComponentList();
//		IServiceDeploy deploy = container.getServiceDeploy();
			
//		Service service = new Service();
		
		//如果成功，这里会被拦截
//		catalog.addService(service);
		
		//此方法未标注@Security，不应被拦截
//		catalog.doNothing();   "D://MyEclipseWorkSpace20151120/org.caeit.cs.sdk_1.0.0/src/log4j.properties"\
		
//		File file = new File("D://test/log4j.xml");		
//		File file = new File("src/log4j.xml");
//		File f = catalog.makeConfiguration("./hello/AAA", file);
//		System.out.println("name: " + f.getName());
		
//		catalog.makeConfiguration("./hello/world/AAAAA");
		monitor.submitData("./we/you/BBB", "1234567890", "extend_monitordata_type");
		
/*		AopTest at = new AopTest();
		File file = catalog.searchConfiguration(at.getClass());
		
		String appenderName = null;
		String paramName = null;
		SAXReader reader = new SAXReader();
	    Document doc;
		try {
			doc = reader.read(file);
			Element root = doc.getRootElement();	    
		    List<Element> appenders = root.elements("appender");
		    for(int i = 0; i<appenders.size(); i++){
		    	appenderName = appenders.get(i).attributeValue("class");
				System.out.println("appenders name: " + appenderName);
				if(appenderName.contains("File")){
					System.out.println("File appender: " + appenderName);
					List<Element> params = appenders.get(i).elements("param");
					for(int j = 0; j<params.size(); j++){
						paramName = params.get(j).attributeValue("value");
						System.out.println("params name: " + paramName);
//						if(paramName.contains("log")){
//							params.get(j).addAttribute("value", "./订票服务/log.log");
//						}
					}
				}
		    }
		} catch (DocumentException e) {
			e.printStackTrace();
		}*/		
		
//		Logger logger = Logger.getLogger(AopTest.class);
		
//		PropertyConfigurator.configure("src/log4j.properties");
//		logger.debug("this is debug message!!!");
//		logger.info("this is info message!!");
//		logger.error("this is error message!");
//		component.addService(service);	 
//		component.doNothing();
//		deploy.manualDeploy(service);
//		
//		deploy.doNothing();
	}
}
