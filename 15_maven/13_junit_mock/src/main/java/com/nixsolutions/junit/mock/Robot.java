package com.nixsolutions.junit.mock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Robot {
	private static final Logger LOG = LogManager.getLogger();

	static final int LOOKING_NORTH = 1;
	static final int LOOKING_EAST = 2;
	static final int LOOKING_SOUTH = 3;
	static final int LOOKING_WEST = 4;

	private int xAxis;
	private int yAxis;
	private int currentFacement = LOOKING_EAST;
	private SaveService saver;

	public Robot(SaveService saver) {
		xAxis = 0;
		yAxis = 0;
		currentFacement = LOOKING_EAST;
		this.saver = saver;
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
	}

	public void turnRight() {
		if (currentFacement < 4) {
			currentFacement++;
		} else {
			currentFacement = 1;
		}
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
		
		saver.save(getCoordinates(), "roboLog.log");
	}

}
