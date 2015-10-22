package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;

import com.nixsolutions.Position.direction;

public class Robot {

	private Position currentPosition;
	private int amountSteps;
	private boolean ready;
	private String logForRobot = "robot.log";
	private BufferedWriter bw;
	File logFolder;

	public Robot() {
		currentPosition = new Position();
		currentPosition.setXY(0, 0);
		currentPosition.setDirection(direction.positiveX);
		amountSteps = 0;
		logFolder = new File(".//temp").getAbsoluteFile();
		ready = true;
	}

	public void setFolderForLog(File pathToFolder) {
		logFolder = pathToFolder;
	}

	public File getLogFile() {
		File resultLog = new File(Paths.get(logFolder.toString(), logForRobot)
				.toString());
		return resultLog;
	}

	public boolean isReadyToGo() {
		return this.ready;
	}

	public void setReadyToGo(boolean status) {
		this.ready = status;
	}

	public Position getPosition() {
		return currentPosition;
	}

	public int passedDistance() {
		return amountSteps;
	}

	public void turnLeft() throws IOException {
		if (isReadyToGo()) {
			switch (currentPosition.getDirection()) {
			case positiveX:
				currentPosition.setDirection(direction.positiveY);
				break;
			case positiveY:
				currentPosition.setDirection(direction.negativeX);
				break;
			case negativeX:
				currentPosition.setDirection(direction.negativeY);
				break;
			case negativeY:
				currentPosition.setDirection(direction.positiveX);
				break;
			default:
				// stay on the same position
				break;
			}
			writeStatusToLogFile();
		}
	}

	public void turnRight() throws IOException {
		if (isReadyToGo()) {
			switch (currentPosition.getDirection()) {
			case positiveX:
				currentPosition.setDirection(direction.negativeY);
				break;
			case positiveY:
				currentPosition.setDirection(direction.positiveX);
				break;
			case negativeX:
				currentPosition.setDirection(direction.positiveY);
				break;
			case negativeY:
				currentPosition.setDirection(direction.negativeX);
				break;
			default:
				// stay on the same position
				break;
			}
			writeStatusToLogFile();
		}
	}

	public void stepForward() throws IOException {
		if (isReadyToGo()) {
			switch (currentPosition.getDirection()) {
			case positiveX:
				currentPosition.setX(currentPosition.X() + 1);
				break;
			case positiveY:
				currentPosition.setY(currentPosition.Y() + 1);
				break;
			case negativeX:
				currentPosition.setX(currentPosition.X() - 1);
				break;
			case negativeY:
				currentPosition.setY(currentPosition.Y() - 1);
				break;
			default:
				// stay on the same position
				break;
			}
			// increase distance
			amountSteps++;
			// write data to log
			writeStatusToLogFile();
		}
	}

	private void writeStatusToLogFile() throws IOException {
		File fileRobot = getLogFile();
		String logForWriting = String.format(
				"Position:(%1s,%2s); Direction:%3s; Passed distance:%4s%n",
				currentPosition.X(), currentPosition.Y(),
				currentPosition.getDirection(), passedDistance());
		try {
			bw = bw == null ? new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileRobot, true))) : bw;
			bw.write(logForWriting);
			bw.flush();
		} catch (IOException ex) {
			throw new IOException();
		}
	}
}
