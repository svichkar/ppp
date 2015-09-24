package com.nixsolutions;

/**
 * Abstract class for Transport
 */
public abstract class Transport {
    private int distance;

    /**
     * Implements turn left of transport
     */
    public void leftTurn() {
	addStepDistance();
    }

    /**
     * Implements turn right of transport
     */
    public void rightTurn() {
	addStepDistance();
    }

    /**
     * Implements move of transport
     */
    public void move() {
	addStepDistance();
    }

    /**
     * @return passed distance by transport
     */
    public int getDistance() {
	return distance;
    }

    /**
     * Sets passed distance by transport
     * 
     * @param d
     *            is distance
     */
    public void setDistance(int d) {
	distance = d;
    }

    /**
     * Abstract method to add distance passed by one step
     */
    public abstract void addStepDistance();
}
