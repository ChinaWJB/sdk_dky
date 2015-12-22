package org.caeit.cs.api.component;

import org.caeit.cs.aop.annotation.Security;
import org.caeit.cs.model.dky.service.Service;

public interface ISrvComponentList {
	@Security(name = "addservice", authority="admin")
	public abstract boolean addService(Service service);
	public void doNothing();
}
