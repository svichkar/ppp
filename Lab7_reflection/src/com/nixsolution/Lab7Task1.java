package com.nixsolution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lab7Task1 {
	private static final Logger LOG = LogManager.getLogger("Refl");
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		TestClass test = new TestClass();
	    test.setString("Test Value");
	    test.setInteger(123);
	    UtilForAnnotation utilForAnnotation = new UtilForAnnotation();
	    String result = utilForAnnotation.getAnotetadField(test);
	    LOG.info(result);   
	}

}
