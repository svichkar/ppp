package com.nixsolutions;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class PathClassLoaderImpl extends ClassLoader implements PathClassLoader {

	private String path;
	//
	// public PathClassLoaderImpl(ClassLoader parent) {
	// super(parent);
	// }

	@Override
	public void setPath(String dir) {
		this.path = dir;

	}

	@Override
	public String getPath() {
		return path;
	}

	public Class<?> findClass(String name) throws ClassNotFoundException {

		byte[] classData;
		Class<?> c;

		try {
			classData = loadClassData(name);
			c = defineClass(name, classData, 0, classData.length);
		} catch (IOException e) {
			c = super.loadClass(name);
		}

		resolveClass(c);
		return c;
	}

	private byte[] loadClassData(String name) throws IOException {

		String pathForLoading = Paths.get(path + name.replace(".", "/") + ".class").toString();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(pathForLoading));
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		int i = 0;
		while ((i = in.read()) != -1) {
			out.write(i);
		}
		in.close();
		byte[] classData = out.toByteArray();
		out.close();
		return classData;
	}
}
