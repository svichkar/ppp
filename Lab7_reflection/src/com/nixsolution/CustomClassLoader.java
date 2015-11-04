package com.nixsolution;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader implements PathClassLoader {
	private String pathtobin;
	private ClassLoader parentClassLoader;

	public CustomClassLoader(String dir, ClassLoader parent) {
		parentClassLoader = parent;
		setPath(dir);
	}

	@Override
	public void setPath(String dir) {
		pathtobin = dir;
	}

	@Override
	public String getPath() {
		return pathtobin;
	}

	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		Class<?> classToReturn = null;
		byte b[];
		if (new File(getAbsolutePath(className)).exists()) {
			try {
				b = fetchClassFromFS(getAbsolutePath(className));
				classToReturn = defineClass(null, b, 0, b.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (classToReturn == null) {
			classToReturn = parentClassLoader.loadClass(className);
		}
		return classToReturn;
	}

	private byte[] fetchClassFromFS(String pathToClassFile) throws IOException {
		Path path = Paths.get(pathToClassFile);
		byte[] bytes = null;
		bytes = Files.readAllBytes(path);
		return bytes;
	}
	
	private String getAbsolutePath(String className){
		return pathtobin + File.separator + className.replace(".", File.separator) + ".class";
	}
	
}
