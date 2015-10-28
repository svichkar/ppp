package com.nixsolutions;

import java.io.FileWriter;
import java.io.IOException;

public class Robot {
    private int coordinateX;
    private int coordinateY;
    private int direction;
    private FileWriter fw;

    public Robot() throws IOException {
	coordinateX = 0;
	coordinateY = 0;
	direction = 90;
    }

    public void turnLeft() {
	if (direction == 0) {
	    direction = 360;
	}
	direction = direction - 90;
    }

    public void turnRight() {
	direction = direction + 90;
	if (direction == 360) {
	    direction = 0;
	}
    }

    public void stepForward() throws IOException {
	switch (direction) {
	case 0:
	    coordinateY++;
	    break;
	case 90:
	    coordinateX++;
	    break;
	case 180:
	    coordinateY--;
	    break;
	case 270:
	    coordinateX--;
	    break;
	default:
	    break;
	}
	writeToFile();
    }

    private void writeToFile() throws IOException {
	fw.write(coordinateX + ";" + coordinateY + "\r\n");
	fw.flush();
	System.out.print(coordinateX + ";" + coordinateY + "\r\n");
    }

    public FileWriter getFw() {
	return fw;
    }

    public void setFw(FileWriter fw) {
	this.fw = fw;
    }

    public void closeFw() throws IOException {
	if (fw != null) {
	    fw.close();
	}
    }

    public void setCoordinateX(int x) {
	coordinateX = x;
    }

    public int getCoordinateX() {
	return coordinateX;
    }

    public void setCoordinateY(int y) {
	coordinateY = y;
    }

    public int getCoordinateY() {
	return coordinateY;
    }

    public void setDirection(int d) {
	direction = d;
    }

    public int getDirection() {
	return direction;
    }
}
