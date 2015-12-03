package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        Path path = Paths.get(getPath(), name.replace(".", FileSystems.getDefault().getSeparator()) + ".class");
            try {
                array = Files.readAllBytes(path);
                temp = defineClass(null, array, 0, array.length);
                LOGGER.trace("Class " + name + " loaded");
                return temp;
            } catch (IOException e) {
                LOGGER.trace("Class not found in " + pathtobin + " loading from system class loader");
                return super.findClass(name);
            }
    }
}
