package com.nixsolutions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Class CustomClassLoader for loading of external classes.
 */
public class CustomClassLoader extends ClassLoader implements PathClassLoader {

	/** The path to class. */
	private String pathToClass = null;

	/**
	 * Instantiates a new custom class loader.
	 *
	 * @param dir the dir
	 * @param parent the parent
	 */
	public CustomClassLoader(String dir, ClassLoader parent) {
		super(parent);
		setPath(dir);
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.PathClassLoader#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String dir) {
		pathToClass = dir;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.PathClassLoader#getPath()
	 */
	@Override
	public String getPath() {
		return pathToClass;
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte[] bytes = getByteData(name);
			return defineClass(name, bytes, 0, bytes.length);
		} catch (FileNotFoundException ex) {
			return super.findClass(name);
		} catch (IOException ex) {
			return super.findClass(name);
		}

	}

	/**
	 * Gets the byte data from external class.
	 *
	 * @param pathToFile the path to file
	 * @return the byte data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private byte[] getByteData(String pathToFile) throws IOException {
		Path path = Paths.get(getPath(), pathToFile.replace(".", FileSystems.getDefault().getSeparator()) + ".class");
		byte[] dataForClass = null;
		dataForClass = Files.readAllBytes(path);
		return dataForClass;
	}

}
