package com.nixsolutions.task2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by svichkar on 12/1/2015.
 */
public class MyClassLoader extends ClassLoader implements PathClassLoader {

    String path;

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
     * overrided method findClass
     *
     * @param name - class name
     * @return Class of some type
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Class<?> clazz;
        Path pathToDir = Paths.get(getPath(), name.replace(".", FileSystems.getDefault().getSeparator()) + ".class");
        try {
            byte[] bytes = Files.readAllBytes(pathToDir);
            clazz = defineClass(name,bytes, 0, bytes.length,null);
            return clazz;
        } catch (IOException e) {
            return super.findClass(name);
        }
    }
}
