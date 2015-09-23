package com.nixsolutions;

/**
 * Extends Transport class for Bus
 */
public class Bus extends Transport{
	public Bus(){
		setCapacity(40);
		setDistance(0);
	}
	
	/**
	 * Implementation of method to add distance passed by one step by bus
	 */
	@Override public void addStepDistance(){
		setDistance(getDistance()+ 9);
	}
}