/**
 * 
 */
package com.nixsolutions;

import java.awt.Point;

/**
 * @author gol4
 *
 */
public abstract class FeometricFigure {

	/**
	 * Name of figure.
	 *
	 * @return the string
	 */
	public abstract String nameOfFigure();

	/**
	 * Moving. The method for moving figure.
	 * 
	 * @param New
	 *            dots for moving figure
	 * 
	 */
	public abstract void moving(Point dots);

	/**
	 * Area. The method for calculating the area
	 * 
	 * @return The area of a figure
	 */
	public abstract double area();

	/**
	 * Change size. Method for changing size of a figure
	 * 
	 * @param coefficient
	 *            for changing size
	 * 
	 */
	public abstract double changeSize(double coefficient);

}
