package org.caeit.cs.api.monitor.monitorservice;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("monitordata")
public class MonitorResource {
	MonitorService ms ;
	
	@POST
	public void submitData(MonitorService monitorservice){
		this.ms = monitorservice;
//		location = al.get(0);
//		data = al.get(1);
//		theme = al.get(2);
		System.out.println("location: " + ms.location);
		System.out.println("data: " + ms.data);
		System.out.println("theme: " + ms.theme);
	}

}
