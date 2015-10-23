package com.nixsolutions.classloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * Class implemented for extending native CLassLoader
 * 
 * @author maxb
 * 
 */
public class MyClassLoader extends ClassLoader implements PathClassLoader {
	String path;
	ClassLoader cloader;

	public MyClassLoader() {

	}

	/**
	 * Second construtor
	 * 
	 * @param path
	 *            Path to external folder
	 * @param cloader
	 *            instance of system class loader
	 */
	public MyClassLoader(String path, ClassLoader cloader) {
		this.cloader = cloader;
		setPath(path);
	}

	/**
	 * Mathod for overloading the native method. Enhence the basic functionality
	 * 
	 */
	@Override
	public Class<?> loadClass(String nameClass) {
		Class<?> result = null;
		byte[] bytes = null;
		try {
			for (File f : getAllFilesFromDir(getPath())) {
				if (getClassNameWitoutPackage(nameClass).equals(
						getNameWithoutExtension(f))) {
					bytes = getFileAsBytes(f);
					result = defineClass(nameClass, bytes, 0, bytes.length);
					if (result.getName().equals(nameClass)) {
						return result;
					}
				}
			}
		} catch (ClassFormatError e1) {
			throw new ClassCastException();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			result = this.cloader.loadClass(nameClass);
		} catch (ClassNotFoundException e) {
			throw new ClassCastException(e.getMessage());
		}
		return result;
	}

	/**
	 * Method for providing a file as array of bytes
	 * 
	 * @param f
	 *            An file
	 * @return An Array of bytes
	 * @throws IOException
	 *             Throwable exception during reading file
	 */
	private byte[] getFileAsBytes(File f) throws IOException {
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(f.toPath());
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		return bytes;
	}

	/**
	 * Method returns as array of files from defined directory
	 * 
	 * @param dir
	 *            An directory
	 * @return An List of files
	 * @throws IOException
	 *             Throwable exception during getting files from directory
	 */
	@SuppressWarnings("unchecked")
	private List<File> getAllFilesFromDir(String dir) throws IOException {
		List<File> lFiles = new ArrayList<File>();
		File folder = new File(dir);
		if (folder.exists()) {
			lFiles = (List<File>) FileUtils.listFiles(folder, null, true);
		} else {
			throw new IOException(String.format(
					"Folder %s with binary files doesn't exist", folder));
		}
		return lFiles;
	}

	/**
	 * Method that returns a base name of file
	 * 
	 * @param file
	 *            A file
	 * @return String as a base name of file
	 */
	private String getNameWithoutExtension(File file) {
		return FilenameUtils.getBaseName(file.getName());
	}

	/**
	 * Method that returns class name without package
	 * 
	 * @param nameClass
	 * @return String as a class name
	 */
	private String getClassNameWitoutPackage(String nameClass) {
		return nameClass.substring(nameClass.lastIndexOf('.') + 1);
	}

	/**
	 * Method set a external folder
	 * 
	 */
	@Override
	public void setPath(String dir) {
		this.path = dir;
	}

	/**
	 * Method returns a external folder
	 * 
	 */
	@Override
	public String getPath() {
		return this.path;
	}

}
