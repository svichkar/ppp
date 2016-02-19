package com.loading;

import org.apache.commons.io.IOUtils;

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

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ClassLoader parentClassLoader = Loader.class.getClassLoader();
        Class loadedClass;
        Loader loader = new Loader(parentClassLoader);
        loader.setPath("com.fieldvalues.ClassWithFields");
        loadedClass = loader.loadClass(loader.getPath(), loader, true);
        System.out.println(loadedClass.getName());
        loader.setPath("D:/FourThreads.class");
        loadedClass = loader.loadClass("FourThreads",loader, false);
        System.out.println(loadedClass.getName());
    }

    public Class loadClass(String name, Loader loader, boolean thisDir) throws ClassNotFoundException, MalformedURLException {
       Class result = null;

        URL classUrl;
        classUrl = new URL("file:///D:/Threads/out/production/Threads/");
        URL[] classUrls = { classUrl };
        URLClassLoader ucl = new URLClassLoader(classUrls);
        Class c = ucl.loadClass("ThreadDemo"); // LINE 14
//        if (thisDir) {
//            result = super.loadClass(name);
//            return result;
//        } else {
//            InputStream is = Loader.class.getClass().getResourceAsStream("D:/FourThreads.class");
//            byte[] b = new byte[0];
//            try {
//                b = IOUtils.toByteArray(is);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
   //         result = defineClass(name, b, 0, b.length);
//            if (result != null) {
//                System.out.println("True");
//            }
////        }
        return c;
    }
    public Class findClass(String name) throws ClassNotFoundException {
        Class res = super.findClass("FourThreads");
        return res;
    }
}


//    public Class startLoading() throws IOException {
//        ClassLoader ldr = Loader.getSystemClassLoader();
//        Loader loader = new Loader();
//        loader.setPath("/com/fieldvalues/UtilityClass.class");
//        InputStream is = Loader.class.getClass().getResourceAsStream(loader.getPath());
//        byte[] b = IOUtils.toByteArray(is);
//        Class result = defineClass("com.fieldvalues.UtilityClass", b, 0, b.length);
//        if (result != null) {
//            System.out.println("True");
//        }
//        Class utClass = ldr.loadClass();
//    }
//}


