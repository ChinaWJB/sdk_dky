package org.caeit.cs.api.catalog;

import java.io.File;

public class TestSearchLog {
	public File searchLog(Class clazz){
		File file = null;
//		file = new File(clazz.getClass().getResource("/log4j.xml").getFile());
		file = new File("src/log4j.xml");
		System.out.println("log4j.xml path: " + file.getAbsolutePath().toString());	
		return file;
	}
}
