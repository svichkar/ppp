package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyAll {

	public static void main(String[] args) {

		File original = new File(
				"/Users/evgeniykryzhanovskiy/Documents/workspace/");
		File copyTo = new File(
				"/Users/evgeniykryzhanovskiy/Documents/test_of_the_io");
		System.out.println("Copy will be executed for the next: \nCopy from: "
				+ original.getAbsolutePath() + "\nCopy to: "
				+ copyTo.getAbsolutePath());

		try {
			getAndCopy(original, copyTo);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public static void getAndCopy(File orig, File copy) throws IOException {
		if (orig.isDirectory()) {
			if (!copy.exists()) {
				copy.mkdirs();
				System.out.println(
						"folder " + orig.getName() + " was copied from "
								+ orig.getParent() + " to " + copy.getParent());
			}
			File[] files = orig.listFiles();
			for (File fil : files) {
				if (fil.compareTo(copy) != 0) {
					getAndCopy(new File(orig, fil.getName()),
							new File(copy, fil.getName()));
				}
			}
		} else {
			InputStream in = new FileInputStream(orig);
			OutputStream out = new FileOutputStream(copy);
			byte[] buffer = new byte[1024];
			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			System.out.println("file " + orig.getName() + " was copied from "
					+ orig.getParent() + " to " + copy.getParent());
		}
	}
}
