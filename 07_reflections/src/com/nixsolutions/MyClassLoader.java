package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader implements PathClassLoader {

	private String path = "";

	@Override
	public void setPath(String dir) {
		path = dir;

	}

	@Override
	public String getPath() {
		return path;
	}

	public Class getInstanceByName(String className) {
		FileInputStream fs = null;
		try {
			File file = new File(path + getAbsoutePath(className));
			fs = new FileInputStream(file);
			byte[] arr = new byte[(int) file.length()];
			fs.read(arr);

			return defineClass(null, arr, 0, arr.length);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
	public String getAbsoutePath(String input) {
		return input.replace('.', '/') + ".class";
	}

}
