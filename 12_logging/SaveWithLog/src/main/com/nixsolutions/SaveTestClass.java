/**
 * 
 */
package main.com.nixsolutions;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @author mixeyes
 *
 */
public class SaveTestClass {

	//private final static Logger logger = Logger.getRootLogger();

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
		//	logger.info("Start SaveTestClass ");
			SaveImplClass saveTest = new SaveImplClass();
			saveTest.enterString();
			saveTest.enterFilePath();
			saveTest.save(saveTest.getStringToSave(), saveTest.getFilePath());
			saveTest.checkIfFileExist();
			saveTest.close();
		} catch (SaveException e) {
			//logger.error(e, e);
		}

	}

}
