package com.nixsolutions;

/**
 * an instance of this class represents a Car which able to move forward, left
 * and right directions with speed equals to 11 points
 * 
 * @author kryzhanovskiy
 *
 */
public class Car extends Vehicle implements Movable {

	@Override
	public void move() {
		this.setDistance(11);
	}

	@Override
	public void turnLeft() {
		this.setDistance(11);
	}

	@Override
	public void turnRight() {
		this.setDistance(11);
	}

}
