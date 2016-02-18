package com.loading;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by pantiukhin on 2/17/2016.
 */
public class Loader extends ClassLoader implements PathClassLoader {

    private String dir;

    public Loader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public void setPath(String dir) {
        this.dir = dir;
    }

    @Override
    public String getPath() {
        return this.dir;
    }

    public static void main(String[] args) {
        Class loadedClass;
        try {
            ClassLoader parentClassLoader = Loader.class.getClassLoader();
            Loader loader = new Loader(parentClassLoader);
            loader.setPath("com.fieldvalues.ClassWithFields");
            loadedClass = loader.loadClass(loader.getPath(), loader, true);
            System.out.println(loadedClass.getName());
            loader.setPath("file:///D:/");
            loadedClass = loader.loadClass("com.fieldvalues.ClassWithFields", loader, false);
            System.out.println(loadedClass.getName());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Class loadClass(String name, Loader loader, boolean thisDir) throws ClassNotFoundException, MalformedURLException {
        Class loadedClass = null;
        if (thisDir) {
            loadedClass = super.loadClass(name);
        } else {
            URL classUrl;
            classUrl = new URL(loader.getPath());
            URL[] classUrls = {classUrl};
            URLClassLoader ucl = new URLClassLoader(classUrls);
            loadedClass = ucl.loadClass(name);
        }
        return loadedClass;
    }
}



