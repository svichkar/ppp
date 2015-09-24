package com.nixsolutions;

/**
 * Extends Transport class for Car
 */
public class Car extends Transport {
    public Car() {
	setDistance(0);
    }

    /**
     * Implementation of method to add distance passed by one step by car
     */
    @Override
    public void addStepDistance() {
	setDistance(getDistance() + 11);
    }
}
