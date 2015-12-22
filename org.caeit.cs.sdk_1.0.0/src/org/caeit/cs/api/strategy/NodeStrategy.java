package org.caeit.cs.api.strategy;

import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;

public class NodeStrategy extends CoreServiceAPI implements INodeStrategy {

	public NodeStrategy(CSApiContainer container) {
		super(container);
	}

}
