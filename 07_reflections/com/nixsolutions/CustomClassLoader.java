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
        Class<?> temp = null;
        byte[] array;
        try {
            array = getClassFromFile(pathtobin + "\\"+ name + ".class");
            temp = defineClass(name, array, 0, array.length);
        } catch (IOException e) {
            LOGGER.trace("Class not found in " + pathtobin, e);
        } finally {
            if (temp != null) {
                LOGGER.trace("Class" + name + " loaded");
                return temp;
            } else {return super.findClass(name);}
        }
    }

    private byte[] getClassFromFile(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }


}
