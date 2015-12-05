package com.nixsolutions.junit.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.logging.MyException;

public class Robot {
	private static final Logger LOG = LogManager.getLogger();

	static final int LOOKING_NORTH = 1;
	static final int LOOKING_EAST = 2;
	static final int LOOKING_SOUTH = 3;
	static final int LOOKING_WEST = 4;

	private int xAxis;
	private int yAxis;
	private int currentFacement = LOOKING_EAST;
	private Path logPath;

	public Robot() {
		xAxis = 0;
		yAxis = 0;
		currentFacement = LOOKING_EAST;
	}

	public int getFacement() {
		return currentFacement;
	}

	public int getXaxis() {
		return xAxis;
	}

	public int getYaxis() {
		return yAxis;
	}

	public String getCoordinates() {
		return "xAxis= " + xAxis + "; yAxis= " + yAxis;
	}

	public void turnLeft() {
		if (currentFacement > 1) {
			currentFacement--;
		} else {
			currentFacement = 4;
		}
		// LOG.debug("turn left");
	}

	public void turnRight() {
		if (currentFacement < 4) {
			currentFacement++;
		} else {
			currentFacement = 1;
		}
		// LOG.debug("turn right");
	}

	public void stepForward() {
		switch (currentFacement) {
		case LOOKING_NORTH:
			xAxis++;
			break;
		case LOOKING_EAST:
			yAxis++;
			break;
		case LOOKING_WEST:
			yAxis--;
			break;
		case LOOKING_SOUTH:
			xAxis--;
			break;
		default:
			break;
		}
		LOG.debug(this.getCoordinates() + " facement: " + this.getFacement());
		
		save(getCoordinates(), "d:\\roboLog.log");
	}

	private void save(String textSave, String filePath) {
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
