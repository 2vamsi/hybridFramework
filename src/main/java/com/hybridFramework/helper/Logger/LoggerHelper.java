
package com.hybridFramework.helper.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.testng.log4testng.Logger;

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
	}