package org.caeit.cs.api.monitor;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.caeit.cs.aop.annotation.Bean;
import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;
import org.caeit.cs.api.config.Config;
/**
 * 文件名称：ServiceMonitor.java
 * 
 * 摘 要：本类为服务目录程序的API类，因为本类的操作方法中含有需要安全认证的方法，因此需要添加相关注解，并且从IServiceMonitor接口中实现
 * 
 * @Security(name = "addservice", authority="admin"),一个类似这样的注解表示，这是一个需要安全权限才能执行的方法，方法名是addservice，所需要的权限是admin权限
 * 
 * 这样就要求，在安全认证软件传递回来的用户信息中，包含了该用户在该系统中的角色，这么一个设定。而且，编程时，这些api的设定者需要知道这些角色，以便知道哪些角色才能
 * 
 * 
 * 创建者：王佳斌
 * 
 * 创建日期：2015年12月10日
 */
@Bean(name="monitor")
public class ServiceMonitor extends CoreServiceAPI implements IServiceMonitor {

	public ServiceMonitor(CSApiContainer container) {
		super(container);
	}
	
	@Override
	public void submitData(String location, String data, String theme){
		String uri = null;
		String s = null;
		WebTarget target = null;
        MonitorService2 ms = new MonitorService2();
		
		ms.location = location;
		ms.data = data;
		ms.theme = theme;
		
//        ArrayList<String> al = new ArrayList<String>();
//        al.add(location);
//        al.add(data);
//        al.add(theme);
        
        Client client = ClientBuilder.newClient();
        
        Config conf = new Config();
        uri = conf.getMonitorService();
        System.out.println("uri: " + uri);
        if(uri==""){
        	
        }else{
        	target = client.target(uri).path("monitordata");
            s = target.request().post(Entity.entity(ms, MediaType.APPLICATION_JSON), String.class);
        }
        System.out.println("monitordata: " + s);
               
//        Response response = target.request().Post(Entity.entity(logservice, MediaType.TEXT_PLAIN),).invoke();
//        Response response1 = target.request().get();
//        File file = response1.readEntity(File.class);
//        System.out.println("log4j.xml path: " + file.getName());
//        response.close();
	}	
}
