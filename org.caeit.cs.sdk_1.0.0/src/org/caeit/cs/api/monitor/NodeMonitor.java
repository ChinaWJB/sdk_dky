package org.caeit.cs.api.monitor;

import org.caeit.cs.aop.container.CSApiContainer;
import org.caeit.cs.api.CoreServiceAPI;

public class NodeMonitor extends CoreServiceAPI implements INodeMonitor {

	public NodeMonitor(CSApiContainer container) {
		super(container);
	}

}
