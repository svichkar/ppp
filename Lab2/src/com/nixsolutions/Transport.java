package com.nixsolutions;

/**
 * Abstract class for Transport
 */
public abstract class Transport {
  private int capacity;
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
   * Returns capacity of transport
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Sets capacity of transport
   * 
   * @param c is capacity
   */
  public void setCapacity(int c) {
    capacity = c;
  }

  /**
   * Returns passed distance by transport
   */
  public int getDistance() {
    return distance;
  }

  /**
   * Sets passed distance by transport
   * 
   * @param d is distance
   */
  public void setDistance(int d) {
    distance = d;
  }

  /**
   * Abstract method to add distance passed by one step
   */
  public abstract void addStepDistance();
}
