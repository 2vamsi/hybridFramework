
package com.hybridFramework.helper.Logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hybridFramework.utility.ResourceHelper;

		
// logger helper is nothing but a class which will give the logger location 
// or this help us to get the object of the logger class
@SuppressWarnings("rawtypes")
		public class LoggerHelper {
	private static boolean root = false;

				public static Logger getLogger(Class clas){
					if (root) { 
						return Logger.getLogger(clas);
					}	
					PropertyConfigurator.configure(ResourceHelper.getResourcePath("/src/main/resources/log4j.properties"));
					root = true;
					return Logger.getLogger(clas);
				}
				
				public static void main(String[] args) {
					Logger log = LoggerHelper.getLogger(LoggerHelper.class);
					System.out.println("Testing logger helper");
					log.info("helper test");
					System.out.println("tested");
				}
	}