/**
 * 
 */
package com.nixsolutions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import exception.Save;

/**
 * @author mixeyes
 *
 */
public class SaveImplClass implements Save, Closeable {
	public SaveImplClass() {
		setStringToSave("");
		setFilePath("");
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private String stringToSave;
	private String filePath;
	private BufferedReader br;

	/**
	 * read text from console
	 * 
	 * @throws IOException
	 */
	protected String readFromConsole() throws IOException {

		String conStr = br.readLine();

		return conStr;
	}

	public void enterString() throws IOException {
		System.out.println("Enter some string:\n");
		setStringToSave(readFromConsole());

	}

	public void enterFilePath() throws IOException {
		System.out.println("Enter full file path:\n");
		setFilePath(readFromConsole());

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
		System.out.println(result ? filePath + " is exist" : filePath + " is not exist");
	}

	@Override
	public void save(String arg0, String arg1) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(filePath);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(stringToSave);

		} catch (FileNotFoundException ex) {
				throw new SaveException("Please Enter valid file name");
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (fileWriter != null)
				try {
					fileWriter.close();
				} catch (IOException e) {
				}
		}
	} // TODO Auto-generated method stub

}
