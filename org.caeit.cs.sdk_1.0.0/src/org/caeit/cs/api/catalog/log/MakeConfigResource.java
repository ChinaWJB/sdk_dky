package org.caeit.cs.api.catalog.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

@Path("makeconfig")
@Singleton
public class MakeConfigResource {
	LogService logservice ;
	MakeConf makeconf;
		
	@POST
	public String makeConfig(LogService logs){	
		logservice = logs;
		makeconf = new MakeConf(logservice);
//		logservice.saveConfiguration();
		return makeconf.makeConfiguration();
	}
	
//    @GET
//    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
//    public File getResult(){
//        return logservice.makeConfiguration();
//    }
}
