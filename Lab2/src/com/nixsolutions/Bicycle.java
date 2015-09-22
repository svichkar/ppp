package com.nixsolutions;

public class Bicycle extends Transport{
	public Bicycle(){
		setCapacity(1);
		setDistance(0);
	}
	@Override public void addStepDistance(){
		setDistance(getDistance()+ 8);
	}
}