package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.nixsolutions.CustomException;

public class CustomSave implements exception.Save {
	
	public void save(String text, String path) {
		if (text.length()==0) {
			throw new CustomException();
		}
		if (path.length()==0) {
			throw new CustomException();
		}
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException ex) {
				throw new CustomException(ex);
			}
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
			fOut.write(text.getBytes());
			fOut.flush();
		} catch (IOException ex) {
			throw new CustomException(ex);
		} finally {
			try {
				if (fOut != null) {
					fOut.close();
				}
			} catch (Exception ex) {
				throw new CustomException(ex);
			}
		}
	}
}
