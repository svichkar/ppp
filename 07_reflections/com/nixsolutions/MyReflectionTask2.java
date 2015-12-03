package com.nixsolutions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * Created by Serko on 27.11.2015.
 */
public class MyReflectionTask2  {
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static void main(String[] args) {
        LOGGER.entry();
        CustomClassLoader loader = new CustomClassLoader();
        loader.setPath("C:\\Users\\kozlovskij\\Desktop");
        try {
            loader.loadClass("com.nixsolutions.TestClass");
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        }
        LOGGER.exit();
    }
}
