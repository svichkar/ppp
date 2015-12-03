/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectionsworkshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mednorcom
 */
public class CustomPathClassLoader extends ClassLoader implements PathClassLoader {

    private String path;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void setPath(String dir) {
        this.path = dir;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    public CustomPathClassLoader() {
        super();
        this.path = null;
    }

    public CustomPathClassLoader(String path, ClassLoader parent) {
        super(parent);
        this.path = path;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        if (this.getPath() != null) {
            try {
                byte b[] = FileUtils.readFileToByteArray(new File(new File(this.getPath(),
                        className.replace(".", "/")).getPath() + ".class"));
                return defineClass(className, b, 0, b.length);
            } catch (FileNotFoundException ex) {
                LOGGER.warn("CustomPathClassLoader failed to load class", ex);
                return super.findClass(className);
            } catch (IOException ex) {
                LOGGER.warn("CustomPathClassLoader failed to load class", ex);
                return super.findClass(className);
            }
        }
        return super.findClass(className);

    }

}
