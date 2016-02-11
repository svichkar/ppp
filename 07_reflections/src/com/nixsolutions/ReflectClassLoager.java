package com.nixsolutions;

/**
 * Created by sobolenko on 2/11/2016.
 */
public class ReflectClassLoager extends ClassLoader implements PathClassLoader{

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

    public void loadClass()
    {

    }
}
