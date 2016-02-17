package com.nixsolutions.ReflectionsTask2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        FileInputStream fis = null;
        byte[] bytes = null;
        if (directory == null) {
            throw new ClassNotFoundException();
        }
        try {
            if (!className.matches(".class")) {
                className = className.replace('.', File.separatorChar) + ".class";
            }
            File classFile = directory.toFile();
            if (!classFile.isAbsolute() || System.getenv().get("HOMEDRIVE")==null) {
                File workDir = new File(System.getProperty("user.dir"));    //transform path to absolute
                Path wd = Paths.get(workDir.toURI());
                Path cf = Paths.get(classFile.toURI());
                Path cn = Paths.get(className);
                directory = wd.resolve(cf.getRoot().relativize(cf)).resolve(cn);
                pathToFile = directory.toFile();
            }
            if (!pathToFile.exists()) {
                throw new FileNotFoundException();
            }
            fis = new FileInputStream(pathToFile);
            long fileLength = pathToFile.length();
            bytes = new byte[(int) fileLength];
            int readResult = 0;
            for (int i = 0; i < fileLength; i++) {
                readResult = fis.read(bytes);
            }
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new ClassNotFoundException(e.getMessage());
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        System.out.println("Load by file");
        return defineClass(directory.getFileName().toString().replace(".class", ""), bytes, 0, bytes.length);
    }
}