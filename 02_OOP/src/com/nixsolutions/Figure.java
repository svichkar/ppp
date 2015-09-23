package com.nixsolutions;

public abstract class Figure {
  /**
   * Array of Figure coordinates
   */
  public double[][] coordinates;
  /**
   * Figure area field
   */
  public double area;

  /**
   * This method could be used for figure movement. Object coordinates on which this method will be
   * called will be changed
   * 
   * @param direction - input string values could be only "forward", "back", "left", "right";
   * @param stepLenght - distance on which object will be moved
   */
  public abstract void move(String direction, double stepLenght);

  /**
   * Method will calculate area of Figure on which will be called
   * 
   * @return area of figure
   */
  public abstract Double areaCalculation();

  /**
   * Method will change size of figure object on which will be called according input coefficient
   * 
   * @param coefficient - resize coefficient;
   */
  public abstract void changeSize(double coefficient);

}
