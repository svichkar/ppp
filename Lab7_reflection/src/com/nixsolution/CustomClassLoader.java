package com.nixsolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader implements PathClassLoader {
	private String pathtobin;

	public CustomClassLoader(String dir, ClassLoader parent) {
		super(parent);
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
	public Class<?> findClass(String className) throws ClassNotFoundException {
		try {
			byte b[] = fetchClassFromFS(pathtobin + File.separator + className + ".class");
			return defineClass(className, b, 0, b.length);
		} catch (FileNotFoundException ex) {
			return super.findClass(className);
		} catch (IOException ex) {
			return super.findClass(className);
		}

	}

	private byte[] fetchClassFromFS(String pathToClassFile) throws IOException {
		Path path = Paths.get(pathToClassFile);
		byte[] bytes = null;
		bytes = Files.readAllBytes(path);
		return bytes;
	}
}
