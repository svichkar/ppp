package com.nixsolutions;

import java.nio.file.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Lab11 {

	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		System.out.println("Source: " + path);
		String pathDestination = path.substring(0, path.lastIndexOf('\\')) + "\\Des";
		copyByIO(path, pathDestination + "IO");
		copyByNIO(path, pathDestination + "NIO");
		copyByApache(path, pathDestination + "Apache");
	}

	public static void copyByIO(String source, String des) {
		copingFiles(new File(source), new File(des));
		System.out.println("Copy to " + des + " complited");
	}

	private static void copingFiles(File source, File des) {
		if (source.isDirectory()) {
			des.mkdir();
			File[] fileList = source.listFiles();
			for (File file : fileList) {
				File newDir = new File(des, file.getName());
				copingFiles(file, newDir);
			}
		} else {
			BufferedReader bufferedReader = null;
			FileOutputStream fileOutputStream = null;
			try {
				FileReader fileReader = new FileReader(source);
				bufferedReader = new BufferedReader(fileReader);
				fileOutputStream = new FileOutputStream(des);
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					fileOutputStream.write(line.getBytes());
					fileOutputStream.write("\n".getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void copyByNIO(String source, String des) {
		copyFilesByNIO(new File(source), new File(des));
		System.out.println("Copy to " + des + " complited");
	}

	private static void copyFilesByNIO(File source, File des) {
		if (source.isDirectory()) {
			des.mkdir();
			File[] fileList = source.listFiles();
			for (File file : fileList) {
				File newDir = new File(des, file.getName());
				copyFilesByNIO(file, newDir);
			}
		} else {
			try {
				Files.copy(source.toPath(), des.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void copyByApache(String source, String des) {
		try {
			FileUtils.copyDirectory(new File(source), new File(des));
			System.out.println("Copy to " + des + " complited");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
