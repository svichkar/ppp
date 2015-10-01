package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CustomSave implements exception.Save {
	
	public void save(String text, String path) {
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
			fOut.write(text.getBytes());
			fOut.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (fOut != null) {
					fOut.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
