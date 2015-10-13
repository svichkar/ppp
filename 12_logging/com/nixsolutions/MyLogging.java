package com.nixsolutions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLogging {
	public static final Logger LOG = LogManager.getLogger("MyLogger");
	
	public MyLogging()
	{
		ClassLoader loader = this.getClass().getClassLoader();
		PropertyConfigurator.configure(loader.getResource("log4j2.xml"));
	}

}
