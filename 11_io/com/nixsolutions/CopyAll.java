package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyAll {

	public static void main(String[] args) {

		File original = new File("D:\\tduty\\lab11_io\\folder_4");
		File copyTo = new File("d:\\all\\Lab11_io_copy\\rtest");
		
		getAndCopy(original, copyTo);
	}

	/**
	 * the method gets two file objects with original and destination folder and
	 * copy all folder and file structure in to the destination folder
	 *
	 * @param orig
	 *            original path from which all items will be copied
	 * @param copy
	 *            a path to which all content of the original folder will be
	 *            copied
	 */
	public static void getAndCopy(File orig, File copy) {
		if (!orig.exists()) {
			System.out.println(
					"please check the original directory, it is not correct");
		} else if (orig.isDirectory()) {
			if (!copy.exists()) {
				copy.mkdirs();
				File[] files = orig.listFiles();
				for (File fil : files) {
					getAndCopy(new File(orig, fil.getName()),
							new File(copy, fil.getName()));
				}
			}
		} else {
			try (InputStream in = new FileInputStream(orig);
					OutputStream out = new FileOutputStream(copy);) {

				byte[] buffer = new byte[1024];

				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
