package com.hybridFramework.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

// this class will basically have 2 methods which will give the locations of the resource 

public class ResourceHelper {
			public static String getResourcePath(String resource) {
			String path = getBaseResourcePath()+ resource;
			return path; 
				}
	
			public static String getBaseResourcePath() {
			String path = System.getProperty("user.dir");
			//System.out.println(path);
			return path; 
				}
	
	// below method can be called wherever we want input stream
	
	public static InputStream getResourcePathInputStream(String path) throws FileNotFoundException {
		return new FileInputStream(ResourceHelper.getResourcePath(path));
	}
	
	public static void main(String[] args) {
		System.out.println(getResourcePath("/config.properties"));
	}
}
