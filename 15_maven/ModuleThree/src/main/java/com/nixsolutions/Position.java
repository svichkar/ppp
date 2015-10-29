package com.nixsolutions;

/**
 * Class for keeping coordinates of a point
 * 
 * @author maxb
 * 
 */
public class Position {

	private int x;
	private int y;
	private direction lookAt;

	public enum direction {
		positiveX, positiveY, negativeX, negativeY
	};

	/**
	 * Getter for X coordinate of point
	 * 
	 * @return
	 */
	public int X() {
		return x;
	}

	/**
	 * Getter for Y coordinate of point
	 * 
	 * @return
	 */
	public int Y() {
		return y;
	}

	/**
	 * Setter for X coordinate
	 * 
	 * @param valX
	 */
	public void setX(int valX) {
		this.x = valX;
	}

	/**
	 * Setter for Y coordinate
	 * 
	 * @param valY
	 */
	public void setY(int valY) {
		this.y = valY;
	}

	/**
	 * Setter for X and Y coordinate
	 * 
	 * @param valX
	 * @param valY
	 */
	public void setXY(int valX, int valY) {
		this.x = valX;
		this.y = valY;
	}

	public void setDirection(direction lookAt) {
		this.lookAt = lookAt;
	}

	public direction getDirection() {
		return this.lookAt;
	}

}
