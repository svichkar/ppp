package com.nixsolutions;

public class Bus extends Transport{
	public Bus(){
		setCapacity(40);
		setDistance(0);
	}
	@Override public void addStepDistance(){
		setDistance(getDistance()+ 9);
	}
}
