/**
 * 
 */
package com.nixsolutions;

import java.io.IOException;

/**
 * @author mixeyes
 *
 */
public class SaveTestClass {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SaveImplClass saveTest = new SaveImplClass();
		saveTest.enterString();
		saveTest.enterFilePath();
		saveTest.save(saveTest.getStringToSave(), saveTest.getFilePath());
		saveTest.checkIfFileExist();
		saveTest.close();

	}

}
