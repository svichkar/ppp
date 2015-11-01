package com.nixsolutions;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Robot {

	public Robot() {
	}

	private Point point = new Point(0, 0);
	private Direction direction = Direction.X;
	private FileWriter fileWriter;
	private File file;

	public Direction turnLeft() {
		switch (direction) {
		case X:
			direction = Direction.Y;
			break;
		case Y:
			direction = Direction.mX;
			break;
		case mX:
			direction = Direction.mY;
			break;
		case mY:
			direction = Direction.X;
			break;
		}
		return direction;
	}

	public Direction turnRight() {
		switch (direction) {
		case X:
			direction = Direction.mY;
			break;
		case Y:
			direction = Direction.X;
			break;
		case mX:
			direction = Direction.Y;
			break;
		case mY:
			direction = Direction.mX;
			break;
		}
		return direction;
	}

	public Point stepForward() throws IOException {
		switch (direction) {
		case X:
			point.x += 1;
			break;
		case Y:
			point.y += 1;
			break;
		case mX:
			point.x -= 1;
			break;
		case mY:
			point.y -= 1;
			break;
		}
		writeCoordinateToFile();
		return point;
	}

	public void setCustomFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	private void writeCoordinateToFile() throws IOException {
		if (fileWriter == null) {
			if (file == null) {
				file = createFile();
			}
			fileWriter = new FileWriter(file, true);
		}
		StringBuilder builder = new StringBuilder();
		builder.append("Coordinates: (").append(point.x).append(", ").append(point.y).append("); Direction: ")
				.append(direction).append("\n");
		String textToSave = builder.toString();
		// FileWriter fileWriter = null;
		try {
			fileWriter.write(textToSave + "\n");
			fileWriter.flush();
		} catch (IOException ex) {
			throw new IOException();
		}/*finally {
			if (fileWriter != null) {
				fileWriter.close();
				fileWriter = null;
			}
		}*/
	}

	private static File createFile() throws IOException {
		String currentdir = System.getProperty("user.dir");
		String fullPath = currentdir + File.separator + "Files" + File.separator
				+ Calendar.getInstance().getTimeInMillis() + "_robot.txt";
		File file = new File(fullPath);
		String folderPath = fullPath.substring(0, fullPath.lastIndexOf(File.separator));
		new File(folderPath).mkdirs();
		file.createNewFile();
		return file;
	}
}

enum Direction {
	X, Y, mX, mY
}
