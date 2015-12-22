package org.caeit.cs.api;

import org.caeit.cs.aop.container.CSApiContainer;

public abstract class CoreServiceAPI {
	
	private CSApiContainer container;
	
	public CoreServiceAPI(CSApiContainer container){
		this.container =  container;
	}
	
	/**
	 * 获得ServiceCatalog对象
	 * @return
	 */
	public Object getServiceCatalog(){
		return this.container.getBean("catalog");
	}

}
