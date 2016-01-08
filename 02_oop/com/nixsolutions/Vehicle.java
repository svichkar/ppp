package com.nixsolutions;

/**
 * abstract class which represents a vehicle with its basic properties. The
 * class implements Comparable interface.
 * 
 * @author kryzhanovskiy
 *
 */
public abstract class Vehicle implements Comparable<Vehicle> {
	private int distance = 0;

	/**
	 * gets total distance traveled by the device
	 * 
	 * @return int value of the
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * set distance of the device
	 * 
	 * @param x
	 *            int value of the distance
	 */
	public void setDistance(int x) {
		this.distance = distance + x;
	}

	@Override
	public int compareTo(Vehicle o) {
		return this.getDistance() - o.getDistance();
	}
}
