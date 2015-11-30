package com.nixsolutions.customclassloader;

import java.io.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rybkinrolla on 28.11.2015.
 */
public class CustomClassLoader extends ClassLoader implements PathClassLoader {
    private String pathNew;
    private static final Logger logg = LogManager.getLogger("UtilTestConvertLogger");

    @Override
    public void setPath(String dir) {
        pathNew = dir;
    }

    @Override
    public String getPath() {
        return pathNew;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] b = FileUtils.readFileToByteArray(new File(pathNew + name + ".class"));
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }
}

