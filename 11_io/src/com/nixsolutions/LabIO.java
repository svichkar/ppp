package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * The Class LabIO.
 */
public class LabIO {

	/** The project directory. */
	private static String projectDirectory = "";

	/** The working directory. */
	private static String workingDirectoryForCopy = "";

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		projectDirectory = System.getProperty("user.dir");
		workingDirectoryForCopy = projectDirectory + "\\DirectoryFor11LabIO";
		File sourceFiles = new File(workingDirectoryForCopy);
		File destFiles = new File(projectDirectory + "/Temp");
		copyStructureDirectoryToDestinationDirectoryWithApacheCommonsIO(sourceFiles, destFiles);
		System.out.println("Directory DirectoryFor11LabIO was copied to directory Temp");

	}

	/**
	 * Copy structure directory to destination directory with apache commons io.
	 *
	 * @param source
	 *            the Coping Directory
	 * @param dest
	 *            the Destination directory
	 */
	private static void copyStructureDirectoryToDestinationDirectoryWithApacheCommonsIO(File source, File dest) {
		try {
			FileUtils.copyDirectoryToDirectory(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
