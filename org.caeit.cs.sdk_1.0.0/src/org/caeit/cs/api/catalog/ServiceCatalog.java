package org.caeit.cs.api.catalog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.caeit.cs.aop.annotation.Bean;
import org.caeit.cs.aop.annotation.Security;
import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;
import org.caeit.cs.api.IOAuthClient;
import org.caeit.cs.api.catalog.LogService2;
import org.caeit.cs.api.config.Config;
import org.caeit.cs.model.dky.service.Service;
import org.caeit.cs.model.dky.service.ServicePack;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：ServiceCatalog.java
 * 
 * 摘 要：本类为服务目录程序的API类，因为本类的操作方法中含有需要安全认证的方法，因此需要添加相关注解，并且从IServiceCatalog接口中实现
 * 
 * @Security(name = "addservice", authority="admin"),一个类似这样的注解表示，这是一个需要安全权限才能执行的方法，方法名是addservice，所需要的权限是admin权限
 * 
 * 这样就要求，在安全认证软件传递回来的用户信息中，包含了该用户在该系统中的角色，这么一个设定。而且，编程时，这些api的设定者需要知道这些角色，以便知道哪些角色才能
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月26日
 * 
 * 完成日期：2015年6月26日
 * 
 */
@Bean(name="catalog")
public class ServiceCatalog extends CoreServiceAPI implements IServiceCatalog, IOAuthClient {

	public ServiceCatalog(CSApiContainer container) {
		super(container);
	}

	SrvCatalogService catalogService = new SrvCatalogService();
	
	/* (non-Javadoc)
	 * @see org.caeit.cs.api.catalog.IServiceCatalog#addService(org.caeit.cs.model.dky.service.Service)
	 */
	@Override
	@Security(name = "addservice", authority="admin")
	public boolean addService(Service service) {
		System.out.println("in catalog addservice!!!");
		return true;
//		ServicePack servicePack = new ServicePack(ServicePack.ADD, service);
//		return catalogService.dealWithService(servicePack);
	}

	/* (non-Javadoc)
	 * @see org.caeit.cs.api.catalog.IServiceCatalog#deleteService(org.caeit.cs.model.dky.service.Service)
	 */
	@Override
	@Security(name = "deleteservice", authority="admin")
	public boolean deleteService(Service service) {
		ServicePack servicePack = new ServicePack(ServicePack.DELETE, service);
		return catalogService.dealWithService(servicePack);
	}

	/* (non-Javadoc)
	 * @see org.caeit.cs.api.catalog.IServiceCatalog#updateService(org.caeit.cs.model.dky.service.Service)
	 */
	@Override
	@Security(name = "updateservice", authority="admin")
	public boolean updateService(Service service) {
		ServicePack servicePack = new ServicePack(ServicePack.UPDATE, service);
		return catalogService.dealWithService(servicePack);
	}
	
	/* (non-Javadoc)
	 * @see org.caeit.cs.api.catalog.IServiceCatalog#search(java.util.Map)
	 */
	@Override
	@Security(name = "search", authority="user")
	public List<Service> search(Map<String, String> queryTerm) {
		return catalogService.searchExactly(queryTerm);
	}

	@Override
	public void doNothing() {
		System.out.println("DoNothing");
	}

	@Override
	public String getClientId() {
		return SrvCatalogService.CLIENT_ID;
	}

	@Override
	public File searchConfiguration(Class clazz) {
		File file = null;
		file = new File(clazz.getClass().getResource("src/log4j.xml").getFile());
		System.out.println("log4j.xml path: " + file.getAbsolutePath().toString());	
		return file;
	}	
	
	@Override
	public void makeConfiguration(String location){		
		ObjectMapper objectMapper = new ObjectMapper();	
    	String configuration = null;
    	String s = null;
    	String uri = null;
    	WebTarget target = null;
    	
    	//查找src目录下的log4j.xml文件，并将其转换成Json字符串形式
    	ArrayList<String> al = readlog4j("src/log4j.xml");
    	configuration = writeReadJson(al);
    	   	
        LogService2 logservice2 = new LogService2();
        logservice2.location = location;
        logservice2.configuration = configuration;
        
        Client client = ClientBuilder.newClient();
        Config conf = new Config();
        uri = conf.getLogService();
        
        //判断系统环境变量logService是否被设置了
        if(uri==""){
        	
        }else{
        	target = client.target(uri).path("makeconfig");
        	s = target.request().post(Entity.entity(logservice2, MediaType.APPLICATION_JSON_TYPE), String.class);
        	saveConfiguration(s);
        }
                
        System.out.println("log4j.xml path: " + s);      
        
        try {
            String[] arr = objectMapper.readValue(s, String[].class);
            System.out.println(arr.length);

            for (int i = 0; i < arr.length; i++) {          	
                System.out.println(arr[i]);   
            } 
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	   /**
     * 2015.12.9 add by wjb
     * 将配置文件转换成ArrayList形式 
     * @param url
     * @return
     */
    public static ArrayList<String> readlog4j(String url){
    	ArrayList<String> al = new ArrayList<String>();
		File f = new File(url);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(f),"UTF-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				al.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return al;
    }
    
    /**
     * 2015.12.9 add by wjb 将配置文件的List形式转换成json字符串，传给客户端
     * @param al
     * @return
     */
    public static String writeReadJson(ArrayList<String> al){
    	ObjectMapper objectMapper = new ObjectMapper();	
    	String s = null;
        try {
	        System.out.println("ObjectMapper");
	        //用objectMapper直接返回list转换成的JSON字符串
	        System.out.println("1###" + objectMapper.writeValueAsString(al));
	        //objectMapper list转换成JSON字符串
//	        objectMapper.writeValue(System.out, al);
	        s = objectMapper.writeValueAsString(al);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return s;
    }
    
	/**
	 * 2015.12.9 add by wjb
	 * 将客户端传来的字符串格式的log4j.xml的配置文件，转换成xml文件的格式存到temp.xml文件中
	 */
	public static void saveConfiguration(String configuration){		
		File f = new File("src/log4j.xml");
		ObjectMapper objectMapper = new ObjectMapper();	
		StringBuffer strBuffer = new StringBuffer();
		PrintWriter printWriter;
    	try {
            String[] arr = objectMapper.readValue(configuration, String[].class);
            System.out.println(arr.length);
            if(!f.exists()){
            	f.createNewFile();
            }
            FileWriter writer = new FileWriter(f, true);
            for (int i = 0; i < arr.length; i++) {          	
//                System.out.println(arr[i]);
                strBuffer.append(arr[i]);
				strBuffer.append(System.getProperty("line.separator"));    
            } 
    		try {
    			printWriter = new PrintWriter("src/log4j.xml");
    			printWriter.write(strBuffer.toString().toCharArray());
    			printWriter.flush();
    			printWriter.close();
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}	
	
//	public static void main(String[] args) {
//		System.out.println("hello world!!!");
//		CSApiContainer container = CSApiContainer.getContainer();
//		IServiceCatalog catalog = container.getServiceCatalog();
//		catalog.searchConfiguration();
//		
//	}
}
