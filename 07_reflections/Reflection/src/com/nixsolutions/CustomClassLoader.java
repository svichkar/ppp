/**
 * 
 */
package com.nixsolutions;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author mixeyes
 *
 */
public class CustomClassLoader extends ClassLoader implements PathClassLoader {

	private String pathToClass;

	/**
	 * @param arg0
	 */
	public CustomClassLoader(ClassLoader parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	public Class<? extends ClassLoader> loadClass() throws ClassNotFoundException {

		if (getPath().contains("com.nixsolutions"))
			return getClass();
		return (Class<? extends ClassLoader>) super.loadClass(pathToClass);

	}

	public Class<?> getCustomClass() throws ClassNotFoundException {
		String file = pathToClass.replace('.', File.separatorChar) + ".class";
		byte[] b = null;
		try {
			// This loads the byte code data from the file
			b = loadClassFileData(file);
			// defineClass is inherited from the ClassLoader class
			// that converts byte array into a Class. defineClass is Final
			// so we cannot override it
			Class<?> c = defineClass(pathToClass, b, 0, b.length);
			resolveClass(c);
			return c;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private byte[] loadClassFileData(String name) throws IOException, ClassNotFoundException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}

	@Override
	public void setPath(String dir) {

		if (dir.isEmpty())
			pathToClass = "";
		else
			this.pathToClass = dir;

	}

	@Override
	public String getPath() {
		return pathToClass;
	}
}
