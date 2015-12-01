package com.nixsolutions.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by svichkar on 12/1/2015.
 */
public class Main {

    public static final Logger LOG = LogManager.getLogger("MyClassLoader");

   public static void main (String args[]) {

       MyClassLoader loader = new MyClassLoader();
       loader.setPath("C:\\gitRepo\\javappp\\08_serialization\\out\\production\\Serialization\\");
       Class loadClass = null;
           try {
               loadClass = loader.loadClass("com.nixsolutions.Serializator");
           } catch (ClassNotFoundException e) {
               LOG.trace(e);
           }

       Object a = null;
       try {
            a = loadClass.newInstance();
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       }

   }




}
