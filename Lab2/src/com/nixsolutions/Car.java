package com.nixsolutions;

public class Car extends Transport {
	public Car(){
		setCapacity(4);
		setDistance(0);
	}
	@Override public void addStepDistance(){
		setDistance(getDistance()+ 11);
	}
}
