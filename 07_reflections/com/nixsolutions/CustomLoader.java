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

	public CustomLoader(ClassLoader parent) {
		super(parent);
	}

	public CustomLoader(String path, ClassLoader parent) {
		super(parent);
		setPath(path);
	}

	@Override
	public Class<?> findClass(String className) throws ClassNotFoundException {
		try {
			byte[] b = getClassFromFile(new File(getPath(), stripName(className) + ".class"));
			return defineClass(className, b, 0, b.length);
		} catch (Exception ex) {
			return super.loadClass(className);
		}
	}
	
	private byte[] getClassFromFile(File f) throws IOException {
		InputStream is = new FileInputStream(f);
		long length = f.length();
		byte[] bytes = new byte[(int) length];
		int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	        && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	    	offset += numRead;
	    }
		is.close();
		return bytes;
	}
	
	private String stripName(String className) {
		return className.substring(className.lastIndexOf('.') + 1);
	}
}
