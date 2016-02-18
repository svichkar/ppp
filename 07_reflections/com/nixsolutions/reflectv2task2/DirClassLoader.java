package com.nixsolutions.reflectv2task2;

import java.io.FileInputStream;
import java.io.IOException;

public class DirClassLoader extends ClassLoader implements PathClassLoader {
    private String dirPath;

    @Override
    public void setPath(String dir) {
        if (dir.endsWith("/")) {
            dirPath = dir;
        } else {
            dirPath = dir + "/";
        }
    }

    @Override
    public String getPath() {
        return dirPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classNamePath = (name.replace(".", "/")) + ".class";
        String fullPath = dirPath + classNamePath;

        FileInputStream stream = null;
        Class<?> foundClass = null;

        try {
            stream = new FileInputStream(fullPath);
            byte[] classData = new byte[stream.available()];
            stream.read(classData);
            foundClass = defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (foundClass != null) {
            return foundClass;
        } else {
            throw new ClassNotFoundException();
        }
    }
}
