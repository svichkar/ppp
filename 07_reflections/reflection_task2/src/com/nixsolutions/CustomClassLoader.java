package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mikhail Sirotkin
 * Class that extends ClassLoader and implements PathClassLoader interface to give us ability execute code using classes
 * from external director that defines by setPath method
 */
public class CustomClassLoader extends ClassLoader implements PathClassLoader {

    private String path;

    /**
     * Method set an external directory for load
     * @param pathToResourceDirectory
     */
    public void setPath(String pathToResourceDirectory){
        this.path = pathToResourceDirectory;

    }
    /**
     * Method returns an external directory that was set up by setPath method
     * @return path
     */
    public String getPath(){
        return path;
    }

    /**
     * Overrided findClass method that use custom class path
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            String wholePath = getPath() + className + ".class";
            byte b[] = fetchClassFromFileStream(wholePath);
            return defineClass(className, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }

    /**
     * Private method for translate class file into byte code
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private byte[] fetchClassFromFileStream(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));
        byte[] bytes = new byte[(int) new File(path).length()];
        is.read(bytes);
        is.close();
        return bytes;
    }
}
