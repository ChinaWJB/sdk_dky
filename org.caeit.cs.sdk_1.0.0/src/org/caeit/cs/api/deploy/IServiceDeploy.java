package org.caeit.cs.api.deploy;

import org.caeit.cs.aop.annotation.Security;
import org.caeit.cs.model.dky.service.Service;

public interface IServiceDeploy {
	@Security(name = "manualDeploy", authority="admin")
	public abstract boolean manualDeploy(Service service);
	
	public void doNothing();
}
