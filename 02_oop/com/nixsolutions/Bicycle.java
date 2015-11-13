package com.nixsolutions;

/**
 * an instance of this class represents a Car which able to move forward, left
 * and right directions with speed equals to 8 points
 * 
 * @author kryzhanovskiy
 *
 */
public class Bicycle extends Vehicle implements Movable {

	@Override
	public void move() {
		this.setDistance(8);
	}

	@Override
	public void turnLeft() {
		this.setDistance(8);
	}

	@Override
	public void turnRight() {
		this.setDistance(8);
	}
}
