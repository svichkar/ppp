package com.nixsolutions;

/**
 * Extends Transport class for Bicycle
 */
public class Bicycle extends Transport {
    public Bicycle() {
	setDistance(0);
    }

    /**
     * Implementation of method to add distance passed by one step by bicycle
     */
    @Override
    public void addStepDistance() {
	setDistance(getDistance() + 8);
    }
}
