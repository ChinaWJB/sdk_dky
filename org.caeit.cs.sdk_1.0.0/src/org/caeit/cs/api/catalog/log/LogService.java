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

/**
 * 2015.12.3 add by wjb
 * 日志服务实现类
 * @author vmuser
 *
 */
public class LogService{
	public String location;
	public String configuration;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}	

//	static{		
//		LogService ils = new LogService();
//		File file = null;
//		TestSearchLog tscl = new TestSearchLog();
//		file = tscl.searchLog(LogService.class);
//		ils.makeConfiguration("./dsd/王", file);
//		System.out.println("end!!!");
//	}
	
	//test
//	public static void main(String[] args) {
////		ILogService ils = new LogService();
////		File file = null;
////		TestSearchLog tscl = new TestSearchLog();
////		file = tscl.searchLog(LogService.class);
////		ils.makeConfiguration("./dsd/王佳斌", file);
//		System.out.println("end!!!");
//	}
	
}
