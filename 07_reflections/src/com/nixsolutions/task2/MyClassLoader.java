package com.nixsolutions.task2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by svichkar on 12/1/2015.
 */
public class MyClassLoader extends ClassLoader implements PathClassLoader {

    String path;

    /**
     * MyClassLoader's constructor
     */
    public MyClassLoader() {
        super(ClassLoader.class.getClassLoader());
    }

    /**
     * gets path
     *
     * @return path to directory
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * sets path
     *
     * @param dir - path to directory
     */
    @Override
    public void setPath(String dir) {

        this.path = dir;
    }

    /**
     * overrided method loadClass
     *
     * @param name - class name
     * @return Class of some type
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return findClass(name);
    }

    /**
     * overrided method findClass
     *
     * @param name - class name
     * @return Class of some type
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = FileUtils.readFileToByteArray(new File(String.format("%s%s.class", path, name.replace(".", "/"))));
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            return super.loadClass(name);
        }
    }
}
