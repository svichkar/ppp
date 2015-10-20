package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class CustomLoader extends ClassLoader implements PathClassLoader {
	private String path;
	private ClassLoader fLoader;

	@Override
	public void setPath(String dir) {
		path = dir;
	}

	@Override
	public String getPath() {
		return path;
	}

	public CustomLoader(ClassLoader fLoader) {
		this.fLoader = fLoader;
	}

	public CustomLoader(String path, ClassLoader fLoader) {
		this.fLoader = fLoader;
		setPath(path);
	}

	@Override
	public Class<?> findClass(String className) throws ClassNotFoundException {
		Class<?> resClass = null;
		try {
			for (File f : getFilesFromDir(getPath())) {
				if (f.getName().equals(stripName(className) + ".class")) {
					byte[] b = getClassFromFile(f);
					resClass = defineClass(className, b, 0, b.length);
					return resClass;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (resClass == null) {
			resClass = fLoader.loadClass(className);
		}
		return resClass;
	}
	
	private byte[] getClassFromFile(File f) throws IOException {
		return Files.readAllBytes(f.toPath());
	}
	
	private List<File> getFilesFromDir(String dir) {
		File folder = new File(dir);
		return (List<File>) FileUtils.listFiles(folder, null, true);
	}
	
	private String stripName(String className) {
		return className.substring(className.lastIndexOf('.') + 1);
	}
}
