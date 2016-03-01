package com.nixsolutions;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Robot implements IRobot {

	private Point coords;
	private ByteArrayOutputStream outStream;
	private FacePosition currentPosition;

	public Robot() {
		coords = new Point(0, 0);
		currentPosition = FacePosition.RIGHT;
	}

	public Robot(ByteArrayOutputStream outStream) {
		this.outStream = outStream;
		coords = new Point(0, 0);
		currentPosition = FacePosition.RIGHT;
	}
	
	public Point getCoords() {
		return coords;
	}

	public void setCoords(Point coords) {
		this.coords = coords;
	}

	public FacePosition getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(FacePosition position) {
		this.currentPosition = position;
	}
	
	public enum FacePosition {
		UP(4), LEFT(3), DOWN(2), RIGHT(1);
		private final int position;

		FacePosition(int position) {
			this.position = position;
		}

		public int getFacePosition() {
			return this.position;
		}
	}

	public void DefineDirection(int offset) {
		int newPosition = currentPosition.getFacePosition() + offset;
		if (newPosition > 4) {
			newPosition -= 4;
		}
		if (newPosition < 1) {
			newPosition += 4;
		}
		switch (newPosition) {
		case 1:
			currentPosition = FacePosition.RIGHT;
			break;
		case 2:
			currentPosition = FacePosition.DOWN;
			break;
		case 3:
			currentPosition = FacePosition.LEFT;
			break;
		case 4:
			currentPosition = FacePosition.UP;
			break;
		default:
			System.out.println("Unknown action.");
			break;
		}
	}

	@Override
	public void turnLeft() {
		DefineDirection(-1);
	}

	@Override
	public void turnRight() {
		DefineDirection(1);
	}

	@Override
	public void stepForward() {
		switch (currentPosition) {
		case UP:
			coords.y++;
			break;
		case LEFT:
			coords.x--;
			break;
		case DOWN:
			coords.y--;
			break;
		case RIGHT:
			coords.x++;
			break;
		default:
			System.out.println("Unknown action.");
			break;
		}
		String location = "Position " + currentPosition.toString() + "; Coordinates [" + coords.x + ";" + coords.y + "]"
				+ "\n";
		try {
			if(outStream != null){
				outStream.write(location.getBytes());
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
