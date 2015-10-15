/**
 * 
 */
package main.com.nixsolutions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.log4j.Logger;

import exception.Save;

/**
 * @author mixeyes
 *
 */
public class SaveImplClass implements Save, Closeable {

	private final static Logger logger = Logger.getLogger(SaveImplClass.class);
	private String stringToSave;
	private String filePath;
	private BufferedReader br;

	public SaveImplClass() {
		setStringToSave("");
		setFilePath("");
		br = new BufferedReader(new InputStreamReader(System.in));
		logger.info("Create SaveImplClass object");
	}

	/**
	 * read text from console
	 * 
	 * @throws IOException
	 */
	protected String readFromConsole() throws IOException {

		String conStr = br.readLine();
		logger.debug(conStr + " was entered from console");

		return conStr;
	}

	public void enterString() throws IOException {
		System.out.println("Enter some string:\n");
		setStringToSave(readFromConsole());
		logger.trace("String was entered");
	}

	public void enterFilePath() throws IOException {
		System.out.println("Enter full file path:\n");
		setFilePath(readFromConsole());
		logger.trace("file path was entered");
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStringToSave() {
		return stringToSave;
	}

	public void setStringToSave(String stringToSave) {
		this.stringToSave = stringToSave;
	}

	@Override
	public void close() throws IOException {
		br.close();

	}

	public void checkIfFileExist() {
		File file = new File(filePath);
		boolean result = file.exists();
		if (result) {
			logger.debug(filePath + " is exist");
		} else {
			logger.error(filePath + " is not exist");
		}
	}

	@Override
	public void save(String arg0, String arg1) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(filePath);
			logger.trace("FileWriter object was created");
			bufferedWriter = new BufferedWriter(fileWriter);
			logger.trace("BufferedWriter object was created");
			bufferedWriter.write(stringToSave);

		} catch (FileNotFoundException ex) {
			logger.fatal(new SaveException("Please Enter valid file name"),
					new SaveException("Please Enter valid file name"));
		} catch (IOException e) {
			logger.fatal(e, e);
		} finally {
			if (fileWriter != null)
				try {
					fileWriter.close();
					logger.trace("FileWriter object was closed");
				} catch (IOException e) {
				}
		}
	} // TODO
		// Auto-generated
		// method
		// stub

}
