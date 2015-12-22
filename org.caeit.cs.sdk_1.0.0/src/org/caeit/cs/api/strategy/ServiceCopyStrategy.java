package org.caeit.cs.api.strategy;

import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;

public class ServiceCopyStrategy extends CoreServiceAPI implements IServiceCopyStrategy {

	public ServiceCopyStrategy(CSApiContainer container) {
		super(container);
	}

}
