package org.caeit.cs.api.strategy;

import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;

public class ServiceRouteStrategy extends CoreServiceAPI implements IServiceRouteStrategy {

	public ServiceRouteStrategy(CSApiContainer container) {
		super(container);
	}

}
