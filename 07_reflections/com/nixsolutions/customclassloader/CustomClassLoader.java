package com.nixsolutions.customclassloader;

import java.io.*;
import org.apache.commons.io.FileUtils;

/**
 * Created by rybkinrolla on 28.11.2015.
 */
public class CustomClassLoader extends ClassLoader implements PathClassLoader {
    private String pathNew;

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
            File filePath = new File((pathNew + name.replace(".",File.separator.toString()) + ".class"));
            byte[] b = FileUtils.readFileToByteArray(filePath);
            return defineClass(null, b, 0, b.length);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }
}

