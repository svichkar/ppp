package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOLab {

	public static void main(String[] args) throws Exception {
		File srcFolder = new File("D:\\EclipseWorkspaceDirectory\\IOLab\\New directory");
		File destFolder = new File("D:\\EclipseWorkspaceDirectory\\IOLab\\Directory destination");

		if (!srcFolder.exists()) {

			System.out.println("Directory does not exist.");
			System.exit(0);

		} else {

			try {
				copyFolder(srcFolder, destFolder);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		System.out.println("Done");
	}

	/**
	 * Copy all contents of source folder to destination folder
	 * 
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFolder(File src, File dest) throws IOException {

		if (src.isDirectory()) {

			if (!dest.exists()) {
				dest.mkdir();
			}

			String files[] = src.list();

			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				copyFolder(srcFile, destFile);
			}

		} else {
			copyFile(src, dest);
		}
	}

	/**
	 * Copy file from source folder to destination folder
	 * 
	 * @param src
	 * @param dest
	 */
	public static void copyFile(File src, File dest) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(src);
			out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
