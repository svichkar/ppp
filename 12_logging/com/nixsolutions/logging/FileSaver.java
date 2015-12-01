package com.nixsolutions.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.Save;

/**
 * an instance of the call saves Strings in to specified directory and class
 * 
 * @author kryzhanovskiy
 *
 */
public class FileSaver implements Save {
	public static final Logger LOG = LogManager.getLogger(FileSaver.class);

	/**
	 * the method saves a string in to the specified directory and file
	 * 
	 * @param textSave
	 *            a String which needs to be saved
	 * @param filePath
	 *            a file and absolute patch to the file in which the string
	 *            needs to be saved
	 * @throws MyException
	 *             if the specified path is not an absolute path
	 * 
	 */
	@Override
	public void save(String textSave, String filePath) {
		LOG.entry(textSave, filePath);
		Path path = Paths.get(filePath);

		try {
			// 1) check if we have a correct (absolute) patch. Custom exception
			// can be thrown
			if (!path.isAbsolute()) {
				throw LOG.throwing(new MyException(
						"the path is not an absolute path, please correct and try again"));
			}

			// 2) create the file if it does not exist
			if (!Files.exists(path)) {
				LOG.info("the file does not exist, it will be created");
				Files.createDirectories(path.getParent());
				Files.createFile(path);
				LOG.info("file {} was created", path);
			} else {
				LOG.warn("file {} will be rewrited", path);
			}

			// 3) write the text in to the file
			Files.write(path, textSave.getBytes(),
					StandardOpenOption.TRUNCATE_EXISTING);
			LOG.info("file {} was sucessfully saved", path);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw LOG.throwing(new RuntimeException(e));
		}
		LOG.exit();
	}
}
