package org.caeit.cs.api.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 2015.12.8 add by wjb
 * @author vmuser
 *
 */
public class Config {
	private static Config conf = new Config();
	private static String logService;
	private static String monitorService;
	
	public static Config newInstance() {
		return conf;
	}
		
	public String getLogService() {
		return logService;
	}

	public void setLogService(String logService) {
		Config.logService = logService;
	}

	public static String getMonitorService() {
		return monitorService;
	}

	public static void setMonitorService(String monitorService) {
		Config.monitorService = monitorService;
	}

	//	private Config() {
	static{
		File f = new File(System.getProperty("user.dir") + File.separator
				+ "node.properties");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String str = "";
			while ((str = br.readLine()) != null) {
				String[] elem = str.split("=");
				if (elem[0].trim().equalsIgnoreCase("logService")) {
					logService = elem[1].trim();
				} else if(elem[0].trim().equalsIgnoreCase("monitorService")){
					monitorService = elem[1].trim();
				} else{
					
				}
			}			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modifyProperties(String key, String value){
		File f = new File(System.getProperty("user.dir") + File.separator
				+ "node.properties");
		System.out.println(System.getProperty("user.dir") + File.separator
				+ "node.properties");
//		File f = new File("src/node.properties");
		BufferedReader reader = null;
		StringBuffer strBuffer = new StringBuffer();
		PrintWriter printWriter;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(f),"UTF-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 判断是否包含关键字，将包含并且不是注释的记录，进行替换
				if (tempString.contains(key)) {
					System.out.println("line " + line + ": " + tempString);
					
					String[] temp1 = tempString.split("=");
					String temp = key + "=" + value;
					tempString = tempString.replace(tempString, temp);
				}
				// 将读取到的所有的记录写到缓冲区中，最后在写到源文件中即可
				strBuffer.append(tempString);
				strBuffer.append(System.getProperty("line.separator"));
				line++;
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
		// 替换后输出的文件位置
		try {
			printWriter = new PrintWriter(f);
			printWriter.write(strBuffer.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Config conf = new Config();
//		conf.modifyProperties("logService", "12345");
		System.out.println(conf.getLogService());
	}
	
	/**
	 * 也就不需要通过配置配置文件查找代理的消息队列
	 */
	/*//根据代理的ip获取对应代理的监听消息队列
	public static String getAgentQueue(String agentIP){
		File file = new File(System.getProperty("user.dir") + File.separator
				+ "conf.properties");
		BufferedReader reader = null;
		String agentQueue = null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
			reader = new BufferedReader(isr);
			String readContent = null;
			int line = 1;
			//一次读入一行，直到读到null 为文件结束
			while((readContent = reader.readLine()) != null){
				if(readContent.contains(agentIP)){
					String[] temp = readContent.split("=");
					agentQueue = temp[1].trim();
				}
				line++;
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return agentQueue;
	}
*/
	
	/**
	 * 代理不需要向管理中心发送消息队列，所以也就不需要插入配置文件
	 * 代理用自己的ip作为消息队列
	 */
	/*//将新添加的代理的消息队列添加到配置文件中
	public static void insertAgentQueue(String content){
		File file = new File(System.getProperty("user.dir") + File.separator
				+ "conf.properties");
		try {
			FileWriter writer = new FileWriter(file,true);
			writer.append(System.getProperty("line.separator"));
			writer.append(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}