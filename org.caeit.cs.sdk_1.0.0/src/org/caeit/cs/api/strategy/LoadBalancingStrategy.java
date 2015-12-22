package org.caeit.cs.api.strategy;

import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;

public class LoadBalancingStrategy extends CoreServiceAPI implements ILoadBalancingStrategy {

	public LoadBalancingStrategy(CSApiContainer container) {
		super(container);
	}

}
