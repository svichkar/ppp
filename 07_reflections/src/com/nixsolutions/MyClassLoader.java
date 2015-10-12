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
	// TODO Auto-generated method stub
	return path;
    }

    public Class getInstanceByPath() {
	FileInputStream fs = null;
	try {
	    File file = new File(path);
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

}
