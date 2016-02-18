package com.manetskiy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class MyClassLoader extends ClassLoader implements PathClassLoader {

    private String dir;

    public MyClassLoader(String dir) {
        setPath(dir);
    }

    public void setPath(String dir) {
        this.dir = dir;
    }

    public String getPath() {
        return dir;
    }

    public Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> toReturn = null;
        try {
            Path fullPath = Paths.get(getPath() + "\\" + name.replace(".", "\\") + ".class");
            byte[] bytes = loadClassData(fullPath);
            toReturn = defineClass(name, bytes, 0, bytes.length);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (toReturn != null)
            return toReturn;
        else
            throw new ClassNotFoundException();
    }

    private byte[] loadClassData(Path path) throws IOException {
        return Files.readAllBytes(path);
    }
}
