package com.nixsolutions;

public abstract class Machine implements Comparable<Machine> {
	private String name = "Machine";
	private int distance = 0;
	private int speed = 0;

	void move() {

		this.distance = distance + getSpeed();
	}

	int getDistance() {
		// System.out.println(distance);
		return distance;

	}

	void turnLeft() {
		this.distance = distance + getSpeed();
	}

	void turnRight() {
		this.distance = distance + getSpeed();
	}

	String getName() {
		// System.out.println(name);
		return name;

	}

	@Override
	public int compareTo(Machine o) {
		// TODO Auto-generated method stub
		return this.getDistance() - o.getDistance();
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
