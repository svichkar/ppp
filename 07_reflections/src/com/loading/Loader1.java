package com.loading;

/**
 * Created by val on 2/28/2016. ClassLoader
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Loader1 extends ClassLoader implements PathClassLoader {
    private String path;

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    public Loader1(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte b[] = getByteArray(getPath() + className.replace(".", File.separator) + ".class");
            return defineClass(null, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.loadClass(className);
        } catch (IOException ex) {
            return super.loadClass(className);
        }
    }

    private byte[] getByteArray(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));
        long length = new File(path).length();
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("File not read to the end " + path);
        }
        is.close();
        return bytes;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Loader1 loader = new Loader1(ClassLoader.getSystemClassLoader());
        loader.setPath("D:/");
        System.out.println("Class with a path specified: " + loader.findClass("ClassWithFields"));
        System.out.println("Class from the current project: " + loader.findClass("com.fieldvalues.ClassWithFields"));
    }
}
