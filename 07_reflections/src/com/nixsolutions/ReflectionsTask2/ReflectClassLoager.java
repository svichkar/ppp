package com.nixsolutions.ReflectionsTask2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by sobolenko on 2/11/2016.
 */
public class ReflectClassLoager extends ClassLoader implements PathClassLoader {

    private Path directory;
    private List<Class> loadedClass;

    public ReflectClassLoager(ClassLoader parent) {
        super(parent);
    }

    public void setPath(String dir) {
        Path folder = null;
        if (!dir.contains(".class")) {
            folder = Paths.get(dir);
        } else {
            folder = Paths.get(dir);
            folder = folder.getParent();
        }
        this.directory = folder;
    }

    public String getPath() {
        return directory.toString();
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        Class findClass = null;
        try {
            findClass = loadClassFromFile(className);
        } catch (IOException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
        return findClass;
    }

    public Class<?> loadClassFromFile(String className) throws ClassNotFoundException, IOException, NullPointerException {
        File pathToFile = null;
        byte[] bytes = null;
        if (directory == null) {
            throw new ClassNotFoundException();
        }
        if (!className.matches(".class")) {
            className = className.replace('.', File.separatorChar) + ".class";
        }
        pathToFile = directory.resolve(className).toFile();
        bytes = loadFileFromFs(pathToFile);
        return defineClass(null, bytes, 0, bytes.length);
    }

    private byte[] loadFileFromFs(File pathToFile) throws IOException {
        byte[] bytes = null;
        if (!pathToFile.exists()) {
            throw new FileNotFoundException();
        }
        bytes = Files.readAllBytes(pathToFile.toPath());
        return bytes;
    }
}