package com.nixsolutions;

public abstract class Transport {
	private int distance;
	private int speed;

	public Transport(int speed){
		setSpeed(speed);
	}
	
	public int getDistance() {
		return distance;
	}

	public int getSpeed() {
		return speed;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void left() {
		setDistance(getDistance() + getSpeed());

	}

	public void right() {
		setDistance(getDistance() + getSpeed());

	}

	public void move(int step) {
		setDistance(getDistance() + getSpeed() * step);

	}

}
