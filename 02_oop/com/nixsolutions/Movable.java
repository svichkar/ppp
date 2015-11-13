package com.nixsolutions;

/**
 * the interface represents movable behavior of a device.
 * 
 * @author kryzhanovskiy
 *
 */
public interface Movable {
	/**
	 * method moves this device in forward direction
	 */
	public void move();

	/**
	 * method turns this device right
	 */
	public void turnLeft();

	/**
	 * method turns this device left
	 */
	public void turnRight();
}
