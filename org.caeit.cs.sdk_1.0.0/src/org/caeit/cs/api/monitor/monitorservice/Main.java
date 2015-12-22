package org.caeit.cs.api.monitor.monitorservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.caeit.cs.api.config.Config;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
	 // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:1144/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in jersey package
        final ResourceConfig rc = new ResourceConfig().packages("org/caeit/cs/api/monitor/monitorservice");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//    	Runtime.getRuntime().exec("cmd.exe /c set " + "monitorService=" + BASE_URI +"/M");//设置logService系统环境变量
    	Config conf = new Config();
    	conf.modifyProperties("monitorService", BASE_URI);
    	
//        try {
//			Process p = Runtime.getRuntime().exec("cmd.exe /c set");
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			String line = null;
//			while((line=br.readLine())!=null){
//				System.out.println(line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        
//        System.out.println("&&&&&&&&&&&&&&&&&&&&&");      
//    	Properties props = System.getProperties();
//    	props.list(System.out);
//    	
//    	System.out.println("%%%W%%%%%%%%%%%%%%%");
//		Map<String, String> map = System.getenv();
//		for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext();){
//			String key = itr.next();
//			System.out.println(key + "=" + map.get(key));
//		}
    	
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
