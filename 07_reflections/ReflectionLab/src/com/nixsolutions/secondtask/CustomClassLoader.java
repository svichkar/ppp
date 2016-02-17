package com.nixsolutions.secondtask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader implements PathClassLoader {

	private String dirPath;

	@Override
	public void setPath(String dir) {
		this.dirPath = dir;
	}

	@Override
	public String getPath() {
		return dirPath;
	}

	public CustomClassLoader() {
	}

	public CustomClassLoader(String dirPath) {
		setPath(dirPath);
	}

	@Override
	public Class<?> findClass(String className) {
		Class<?> result = null;
		try {
			System.out.printf("Binary name: %s%nLocation: %s%n", className, getPath());
			Path filePath = Paths.get(getPath(), className.replace(".", File.separator) + ".class");
			byte[] dataArr = Files.readAllBytes(filePath);
			result = defineClass(null, dataArr, 0, dataArr.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
