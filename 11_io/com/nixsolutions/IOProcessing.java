package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;

public class IOProcessing {

	public static void main(String[] args) {
		
		File source = new File("D:\\AllTestGarbage\\FolderTest");		
		if (!source.exists()) {
			System.out.println("Source directory does not exist.");
			System.exit(0);
		}
		//io
		File destIO = new File("D:\\AllTestGarbage\\FolderDest_IO");
		copyIO(source, destIO);
		//nio
		File destNIO = new File("D:\\AllTestGarbage\\FolderDest_NIO");
		try {
			copyNIO(source, destNIO);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		//commons
		File destCommons = new File("D:\\AllTestGarbage\\FolderDest_Commons");
		try {
			FileUtils.copyDirectory(source, destCommons);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private static void copyIO(File source, File dest) {
		if (source.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdir();
			}
			String[] files = source.list();
			for (String fileName : files) {
				File srcFile = new File(source, fileName);
				File destFile = new File(dest, fileName);
				copyIO(srcFile, destFile);
			}
		} else {
			InputStream in = null;
	        OutputStream out = null;
	        try {
	        	in = new FileInputStream(source);
	            out = new FileOutputStream(dest);
	        	byte[] buffer = new byte[1024];
	        	int length;
	            while ((length = in.read(buffer)) > 0)
	            {
	                out.write(buffer, 0, length);
	            }
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	        } finally {
	        	try {
	        		if (in != null) {
	        			in.close();
	        		}
	        		if (out != null) {
	        			out.close();
	        		}
	        	} catch (Exception ex) {
	        		ex.printStackTrace();
	        	}
	        }
		}
	}
	
	private static void copyNIO(File source, File dest) throws IOException {
		if (source.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdir();
			}
			String[] files = source.list();
			for (String fileName : files) {
				File srcFile = new File(source, fileName);
				File destFile = new File(dest, fileName);
				copyNIO(srcFile, destFile);
			}
		} else {
			Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}
}
