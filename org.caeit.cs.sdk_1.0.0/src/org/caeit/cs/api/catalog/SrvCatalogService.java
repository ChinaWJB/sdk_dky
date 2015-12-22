package org.caeit.cs.api.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.client.dky.ServiceCatalogClient;
import org.caeit.cs.model.dky.service.Service;
import org.caeit.cs.model.dky.service.ServicePack;
import org.caeit.cs.util.CoreServiceProperties;

/**
 * Copyright (c) 2015,电子科学研究院 All rights reserved.
 * 
 * 文件名称：SrvCatalogService.java
 * 
 * 摘 要：为ServiceCatalog提供服务的类，进行相关的逻辑操作
 * 
 * 
 * 创建者：黄飞龙
 * 
 * 创建日期：2015年6月24日
 * 
 * 完成日期：2015年6月24日
 * 
 */

public class SrvCatalogService {
	private static final String URL_PREFIX = "http://";
	private static final String URL_POSTFIX_GPSCATALOG = "/gas/AllOthers/gpscatalog.action?";
	private static final String URL_POSTFIX_SEARCHEXACTLY = "/gas/AllOthers/doSearchExactly.action?";
	public static final String CLIENT_ID = "DomainManagement";
	ServiceCatalogClient catalogClient = new ServiceCatalogClient();

	public boolean dealWithService(ServicePack servicePack) {
		String gpsCatalogUrl = URL_PREFIX
				+ CoreServiceProperties.serviceCatalogIP + ":"
				+ CoreServiceProperties.serviceCatalogPort
				+ URL_POSTFIX_GPSCATALOG;
		List<ServicePack> packList = new ArrayList<ServicePack>();
		packList.add(servicePack);
		try {
			catalogClient.invoke(gpsCatalogUrl, packList);
			return true;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Service> searchExactly(Map<String, String> queryTerm) {
		String gpsCatalogUrl = URL_PREFIX
				+ CoreServiceProperties.serviceCatalogIP + ":"
				+ CoreServiceProperties.serviceCatalogPort
				+ URL_POSTFIX_SEARCHEXACTLY;
		return catalogClient.invoke(gpsCatalogUrl, queryTerm);
	}
}
