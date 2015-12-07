package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class MyClassLoader extends ClassLoader implements PathClassLoader {
	private String directory;

	@Override
	public void setPath(String dir) {
		this.directory = dir;

	}

	@Override
	public String getPath() {
		return this.directory;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			File filePath = new File((directory + name.replace(".", File.separator.toString()) + ".class"));
			byte[] b = FileUtils.readFileToByteArray(filePath);
			return defineClass(null, b, 0, b.length);
		} catch (IOException e) {
			return super.findClass(name);
		}

	}
}
