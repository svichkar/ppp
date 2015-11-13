package com.nixsolutions;

/**
 * an instance of this class represents a Bus which able to move forward, left
 * and right directions with speed equals to 9 points
 * 
 * @author kryzhanovskiy
 *
 */
public class Bus extends Vehicle implements Movable {

	@Override
	public void move() {
		this.setDistance(9);
	}

	@Override
	public void turnLeft() {
		this.setDistance(9);
	}

	@Override
	public void turnRight() {
		this.setDistance(9);
	}
}
