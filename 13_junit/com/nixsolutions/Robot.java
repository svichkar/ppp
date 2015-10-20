package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Robot {
	private Direction direction;
	private int coordX;
	private int coordY;
	private File logLocation;

	public Robot(File logLocation) {
		direction = Direction.XP;
		coordX = 0;
		coordY = 0;
		if (logLocation.isDirectory()) {
			this.logLocation = new File(logLocation, "robotLog.log");
		} else {
			this.logLocation = logLocation;
		}
		
	}

	public void stepForward() throws IOException {
		switch (this.direction) {
		case XP:
			coordX += 1;
			break;
		case YP:
			coordY += 1;
			break;
		case XN:
			coordX -= 1;
			break;
		case YN:
			coordY -= 1;
			break;
		}
		writeToLog();
	}

	public void turnLeft() {
		direction = Direction.getDirectionByInt(((direction.getInt() + 1) % 4));
	}

	public void turnRight() {
		direction = Direction.getDirectionByInt(((direction.getInt() + 3) % 4));
	}

	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	private void writeToLog() throws IOException {
		if (!logLocation.exists()) {
			logLocation.createNewFile();
		}
		OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(logLocation));
		fos.write(String.format("Coordinate X: %1$s. Coordinate Y: %2$s.", coordX, coordY));
		fos.flush();
		fos.close();
	}

	public enum Direction {
		XP(0), YP(1), XN(2), YN(3);

		private int intCoord;

		private void setInt(int value) {
			intCoord = value;
		}

		public int getInt() {
			return intCoord;
		}

		private Direction(int coordVal) {
			setInt(coordVal);
		}

		public static Direction getDirectionByInt(int value) {
			switch (value) {
				case 0:
					return Direction.XP;
				case 1:
					return Direction.YP;
				case 2:
					return Direction.XN;
				case 3:
					return Direction.YN;
				default:
					return null;
			}
		}
	}
}
