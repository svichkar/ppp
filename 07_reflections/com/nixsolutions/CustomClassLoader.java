package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Serko on 28.11.2015.
 */
public class CustomClassLoader extends ClassLoader implements PathClassLoader {
    private static final Logger LOGGER = LogManager.getLogger("CustomClassLoader");
    private String pathtobin;

    @Override
    public void setPath(String dir) {
        this.pathtobin = dir;
    }

    @Override
    public String getPath() {
        return this.pathtobin;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> temp;
        byte[] array;
        File file = new File(pathtobin + "\\"+ shortName(name) + ".class");
        if (file.exists()) {
            try {
                array = Files.readAllBytes(file.toPath());
                temp = defineClass(name, array, 0, array.length);
                LOGGER.trace("Class " + name + " loaded");
                return temp;
            } catch (IOException e) {
                LOGGER.trace("Class not found in " + pathtobin + "unable to get byte array", e);
                return super.findClass(name);
            }
        } else {
            LOGGER.trace("Class not found in " + pathtobin + " loading from system class loader");
            return super.findClass(name);
        }
    }

    protected String shortName (String name) {
        name = name.substring(name.lastIndexOf('.') + 1);
        return name;
    }
}
