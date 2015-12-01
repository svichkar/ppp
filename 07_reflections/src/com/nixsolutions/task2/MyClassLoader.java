package com.nixsolutions.task2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by svichkar on 12/1/2015.
 */
public class MyClassLoader extends ClassLoader implements PathClassLoader {

    String path;

    public MyClassLoader() {
        super(MyClassLoader.class.getClassLoader());
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public void setPath(String dir) {

        this.path = dir;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return findClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes =  FileUtils.readFileToByteArray(new File(String.format("%s%s.class", path, name.replace(".", "/"))));
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }
}
