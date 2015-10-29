/**
 * 
 */
package com.nixsolutions;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mixeyes
 *
 */
public class CustomClassLoader extends ClassLoader implements PathClassLoader {
	private Map<String, Class> classes = new HashMap<String, Class>();
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

	public Class<?> getCustomClass(String className) throws IOException, ClassNotFoundException {
		byte classByte[];
		Class result = null;

		result = classes.get(className);
		if (result != null) {
			return result;
		}
		try {
			result = findSystemClass(className);
			if (result != null) {
				return result;
			}
		} catch (ClassNotFoundException e) {

		}
		String[] filename = className.split("\\.");
		String file = filename[filename.length - 1] + ".class";
		try {
			classByte = loadClassFileData(pathToClass + file);
			result = defineClass(className, classByte, 0, classByte.length, null);
			classes.put(className, result);

			return result;
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	private byte[] loadClassFileData(String classPath) throws IOException, ClassNotFoundException {
		InputStream is = new BufferedInputStream(new FileInputStream(classPath));
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		int nextValue = is.read();
		while (-1 != nextValue) {
			byteStream.write(nextValue);
			nextValue = is.read();
		}

		return byteStream.toByteArray();
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
