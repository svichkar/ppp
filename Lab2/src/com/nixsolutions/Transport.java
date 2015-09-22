package com.nixsolutions;

public abstract class Transport {
	private int capacity;
	private int distance;
	public void leftTurn(){
		addStepDistance();
	}
	public void rightTurn(){
		addStepDistance();
	}
	public void move(){
		addStepDistance();
	}
	public int getCapacity(){
		return capacity;
	}
	public void setCapacity(int c){
		capacity = c;
	}
	public int getDistance(){
		return distance;
	}
	public void setDistance(int d){
		distance = d;
	}
	public abstract void addStepDistance();
}
