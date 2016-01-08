package com.nixsolutions.junit.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveService {
	private static final Logger LOG = LogManager.getLogger();
	private Path filePath;
	
	public SaveService(String path){
		filePath = Paths.get(path);
	}
	
	public void save(String textSave, String fileName) {
		LOG.entry(textSave, filePath);
		Path path = Paths.get(filePath.toString(), fileName);

		try {
			// 1) create the file if it does not exist
			if (!Files.exists(path)) {
				LOG.info("the file does not exist, it will be created");
				Files.createDirectories(path.getParent());
				Files.createFile(path);
				LOG.info("file {} was created", path);
			} else {
				LOG.warn("file {} will be rewrited", path);
			}
			// 2) write the text in to the file
			Files.write(path, (textSave + "\r\n").getBytes(),
					StandardOpenOption.APPEND);
			LOG.info("file {} was sucessfully saved", path);
		} catch (IOException e) {
			throw LOG.throwing(new RuntimeException(e));
		}
		LOG.exit();
	}
}
