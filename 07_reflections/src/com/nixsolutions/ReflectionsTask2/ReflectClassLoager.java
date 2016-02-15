package com.nixsolutions.ReflectionsTask2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by sobolenko on 2/11/2016.
 */
public class ReflectClassLoager extends ClassLoader implements PathClassLoader {

    private String directory;

    public ReflectClassLoager(ClassLoader parent) {
        super(parent);
    }

    public void setPath(String dir) {
        this.directory = dir;
    }

    public String getPath() {
        return directory;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        super.loadClass(className);
        File classFile = new File(directory);
        byte[] bytes = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(classFile);
            long fileLength = classFile.length();
            bytes = new byte[(int) fileLength];
            int readResult = 0;
            for (int i = 0; i < fileLength; i++) {
                readResult = fis.read(bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        return defineClass(className, bytes, 0, bytes.length);
    }
}