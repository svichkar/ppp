package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomLoader extends ClassLoader implements PathClassLoader {
	private String path;

	@Override
	public void setPath(String dir) {
		path = dir;
	}

	@Override
	public String getPath() {
		return path;
	}

	public CustomLoader() {

	}

	public CustomLoader(String path) {
		setPath(path);
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		if (path == null || path == "") {
			return super.findSystemClass(name);
		} else {
			File dir = new File(getPath());
			if (dir.exists()) {
				String[] files = dir.list();
				for (String fileName : files) {
					if (fileName.equals(stripName(name) + ".class")) {
						File f = new File(dir, fileName);
						long size = f.length();
						byte[] buffer = new byte[(int) size];
						InputStream in = null;
						try {
							in = new FileInputStream(f);
							in.read(buffer);
							Class<?> debugClass = defineClass(normalize(name),
									buffer, 0, buffer.length);
							return debugClass;
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				throw new ClassNotFoundException();
			}
		}
		return null;
	}
	
	private String normalize(String className) {
		return className.replace('/', '.');
	}
	
	private String stripName(String className) {
		return className.substring(className.lastIndexOf('.') + 1);
	}
}
