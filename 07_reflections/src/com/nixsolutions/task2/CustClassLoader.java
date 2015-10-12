package com.nixsolutions.task2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustClassLoader extends ClassLoader implements PathClassLoader {
    private String path;
    private Map<String, Class> cashedClasses = new HashMap<String, Class>();

    public CustClassLoader() {
	super(ClassLoader.getSystemClassLoader());
    }

    public Class loadCustClass(String className) throws IOException {
	Class custClass = cashedClasses.get(className);
	if (custClass != null) {
	    return custClass;
	} else {
	    BufferedInputStream inStream = null;
	    try {
		String[] tempFileName = className.split("\\.");
		String fileName = tempFileName[tempFileName.length - 1];
		File classFile = new File(path + File.separator + fileName + ".class");
		inStream = new BufferedInputStream(new FileInputStream(classFile));
		byte[] classByte = new byte[inStream.available()];
		inStream.read(classByte);
		cashedClasses.put(className, defineClass(null, classByte, 0, classByte.length));
		return cashedClasses.get(className);
	    } finally {
		if (inStream != null) {
		    inStream.close();
		}
	    }
	}

    }

    @Override
    public void setPath(String dir) {
	this.path = dir;
    }

    @Override
    public String getPath() {
	return this.path;
    }
}
