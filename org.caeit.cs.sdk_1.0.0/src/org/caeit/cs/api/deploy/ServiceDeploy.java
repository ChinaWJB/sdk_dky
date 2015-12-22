package org.caeit.cs.api.deploy;

import org.caeit.cs.aop.annotation.Bean;
import org.caeit.cs.aop.annotation.Security;
import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;
import org.caeit.cs.model.dky.service.Service;

@Bean(name="deploy")
public class ServiceDeploy extends CoreServiceAPI implements IServiceDeploy{

	public ServiceDeploy(CSApiContainer container) {
		super(container);
	}
	
	@Override
	@Security(name = "manualDeploy", authority="admin")
	public boolean manualDeploy(Service service) {
		return true;
	}

	@Override
	public void doNothing() {
		System.out.println("Do nothing!!!");
		
	}
}
