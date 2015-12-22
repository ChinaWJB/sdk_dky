package org.caeit.cs.api.component;

import org.caeit.cs.aop.annotation.Bean;
import org.caeit.cs.aop.annotation.Security;
import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;
import org.caeit.cs.api.IOAuthClient;
import org.caeit.cs.model.dky.service.Service;

@Bean(name="component")
public class SrvComponentList extends CoreServiceAPI implements ISrvComponentList, IOAuthClient{

	public SrvComponentList(CSApiContainer container) {
		super(container);
	}

	@Override
	@Security(name = "addservice", authority="admin")
	public boolean addService(Service service) {
		//TODO Auto-generated method stub
		System.out.println("component addService!!!");
		return true;
	}

	@Override
	public void doNothing() {
		System.out.println("do nothing!!!");
	}

	@Override
	//实现的IOAuthClient中的方法
	public String getClientId() {
		// TODO Auto-generated method stub
		return "DomainManagement";
	}
}
