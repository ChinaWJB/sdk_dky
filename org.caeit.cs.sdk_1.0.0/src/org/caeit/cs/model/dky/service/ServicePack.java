package org.caeit.cs.model.dky.service;
public class ServicePack {
	
	public static final String ADD = "add";
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	
	private String oper;
	private Service service;

	public ServicePack(String oper, Service service) {
		this.oper = oper;
		this.service = service;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}