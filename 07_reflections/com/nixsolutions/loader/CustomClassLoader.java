package com.nixsolutions.loader;

import java.io.*;
import java.nio.file.Files;

public class CustomClassLoader extends ClassLoader implements PathClassLoader {

    private String pathToClass;

    public CustomClassLoader(String pathToClass) {
        setPath(pathToClass);
    }

    @Override
    protected Class findClass(String className) throws ClassNotFoundException {
        try {
            byte b[] = loadClassData(className);
            return defineClass(className, b, 0, b.length);
        } catch (IOException e) {
            /*
            In my opinion, it's need because if we catch IOException we don't know why happens this
            We redirect to super class (ClassLoader) attempt to find and to load class if need
             */
            return super.findSystemClass(className);
        }
    }

    private byte[] loadClassData(String className) throws IOException {
        String correctClassName = className.contains(".") ? String.join(File.separator, className.split("\\."))
                : className;
        return Files.readAllBytes(new File(pathToClass + correctClassName + ".class").toPath());
    }

    @Override
    public void setPath(String dir) {
        if (!(dir.endsWith("\\") || dir.endsWith("/"))) {
            this.pathToClass = dir + File.separator;
        } else {
            this.pathToClass = dir;
        }
    }

    @Override
    public String getPath() {
        return pathToClass;
    }

}
