package org.caeit.cs.client.dky;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.caeit.cs.model.dky.service.Service;
import org.caeit.cs.model.dky.service.ServicePack;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：ServiceCatalogClient.java
 * 
 * 摘 要：用来访问服务目录提供的服务的客户端类
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月24日
 * 
 * 完成日期：2015年6月24日
 * 
 */
public class ServiceCatalogClient {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 调用本域的全局地址服务
	 * 
	 * @param gpsOperAddr
	 * @param servicePackList
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public void invoke(String gpsCatalogUrl, List<ServicePack> servicePackList)
			throws ClientProtocolException, IOException {
		String servicePackListJson = makeServicePackListJsonString(servicePackList);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(gpsCatalogUrl);
		httpPost.setHeader("Content-Type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(servicePackListJson, "UTF-8"));
			httpclient.execute(httpPost);
		} finally {
			httpPost.releaseConnection();
		}
	}

	private String makeServicePackListJsonString(List<ServicePack> list) {
		try {
			return objectMapper.writeValueAsString(list);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 调用本域的全局地址服务
	 * 
	 * @param gpsOperAddr
	 * @param servicePackList
	 */
	public List<Service> invoke(String gpsCatalogUrl,
			Map<String, String> queryTerm) {
		for (String key : queryTerm.keySet()) {
			String appened = "field=" + key + "&term=" + queryTerm.get(key);
			gpsCatalogUrl += appened;
		}
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(gpsCatalogUrl);
		httpGet.setHeader("Content-Type", "application/json");
		try {
			HttpResponse httpResponse = httpclient.execute(httpGet);
			// 将数据读入JsonNode
			List<Service> serviceList = objectMapper.readValue(httpResponse
					.getEntity().getContent(),
					new TypeReference<ArrayList<Service>>() {
					});
			return serviceList;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpGet.releaseConnection();
		}
		return null;
	}
}
