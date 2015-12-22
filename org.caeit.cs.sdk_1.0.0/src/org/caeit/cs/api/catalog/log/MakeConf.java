package org.caeit.cs.api.catalog.log;

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
import java.util.ArrayList;
import java.util.List;

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

public class MakeConf {
	LogService logservice;

	MakeConf(LogService logservice){
		this.logservice = logservice;
	}
	/**
	 * 2015.12.9 add by wjb
	 * 将客户端传来的字符串格式的log4j.xml的配置文件，转换成xml文件的格式存到temp.xml文件中
	 */
	public void saveConfiguration(){		
		File f = new File("src/temp.xml");
		ObjectMapper objectMapper = new ObjectMapper();	
		StringBuffer strBuffer = new StringBuffer();
		PrintWriter printWriter;
    	try {
            String[] arr = objectMapper.readValue(logservice.configuration, String[].class);
            System.out.println(arr.length);
            if(!f.exists()){
            	f.createNewFile();
            }
            FileWriter writer = new FileWriter(f, true);
            for (int i = 0; i < arr.length; i++) {          	
                System.out.println(arr[i]);
                strBuffer.append(arr[i]);
				strBuffer.append(System.getProperty("line.separator"));    
            } 
    		try {
    			printWriter = new PrintWriter("src/temp.xml");
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
	
	/**
	 * 读取temp.xml文件，对其进行更改，
	 * 并调用readtemp将配置文件转换成ArrayList格式，
	 * 然后再调用writeReadJson将ArrayList格式转换成Json字符串格式，返回给客户端
	 * @return
	 */
	public String makeConfiguration(){	
		saveConfiguration();
		ArrayList<String> al = new ArrayList<String>();
		String config = null;
		File file = new File("src/temp.xml");
//		if(configuration == null){	//如果没有传配置文件
//			return null;
//		}else{		
			String[] loc = logservice.location.split("/");
			String serviceName = loc[loc.length-1];
			
			String appenderName = null;
			String paramName =null;
			SAXReader reader = new SAXReader();
		    Document doc = null;
			try {
				doc = reader.read(file);
			} catch (DocumentException e2) {
				e2.printStackTrace();
			}
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
						if(paramName.contains("log")){
							params.get(j).addAttribute("value", "./" + serviceName +"/log.log");
						}
					}
				}
		    }
		    OutputStream os;
			try {
				os = new FileOutputStream(file);
			    XMLWriter writer;
			    writer = new XMLWriter(os, OutputFormat.createPrettyPrint());
				writer.write(doc);
				writer.flush();
				writer.close();
				os.close();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
			al = readtemp("src/temp.xml");
			config = writeReadJson(al);
			return config;
//		}
	}
	
	   /**
     * 2015.12.9 add by wjb
     * 将配置文件转换成ArrayList形式 
     * @param url
     * @return
     */
    public static ArrayList<String> readtemp(String url){
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
	
}
