package com.nixsolutions.customclassloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rybkinrolla on 28.11.2015.
 */
public class CustomClassLoaderTest {
    private static final Logger LOGG = LogManager.getLogger("CustomClassLoaderTest");
    public static void main(String[] args){
        CustomClassLoader ssl = new CustomClassLoader();
        ssl.setPath("D:\\JavaProjects\\javappp\\out\\production\\javappp\\");
        try {
            Class c = ssl.loadClass("com.nixsolutions.Foursquare44");
        } catch (ClassNotFoundException e) {
            LOGG.catching(e);
        }
    }
}
