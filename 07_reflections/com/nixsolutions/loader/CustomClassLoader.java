package com.nixsolutions.loader;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CustomClassLoader extends ClassLoader implements PathClassLoader {

    private String pathToClass;
    //private Map<String, Class> cache = new HashMap<>();

    @Override
    protected Class findClass(String className) throws ClassNotFoundException {
        try {
            //Class<?> ret = cache.get(className);
            Class<?> ret = null;
            //check hui znaet che
/*            try {
                if (ret == null) {
                    ret = super.findSystemClass(className);
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }*/
            if (ret == null) {
                byte b[] = loadClassData(className);
                ret = defineClass(className, b, 0, b.length);
                //cache.put(className, ret);
            }
            return ret;
        } catch (IOException e) {
            throw new ClassNotFoundException(className);
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
