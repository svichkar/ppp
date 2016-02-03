package com.nixsolutions.figure;

import java.awt.geom.Point2D;

/**
 * Figure is the abstract base class which represents a sample of geometric
 * object defined by its coordinates on the two-dimensional Euclidean plane and
 * contains methods of basic geometric transformations.
 * 
 * @author baliuga
 */
public abstract class Figure {
	/**
	 * An array of points represented by an ordered pair (x, y) of numbers,
	 * where the first number conventionally represents the horizontal, and the
	 * second number conventionally represents the vertical.
	 * 
	 * @see Point2D.Double
	 */
	private Point2D.Double[] coords;

	/**
	 * Gets the array of points (coordinates) that specify the location of the
	 * figure component on the Euclidean plane.
	 * 
	 * @return Array of coordinates for figure component.
	 */
	public Point2D.Double[] getCoords() {
		return coords;
	}

	/**
	 * Sets the array of points (coordinates) of the figure component.
	 * 
	 * @param coords
	 *            Array of coordinates for figure component.
	 */
	public void setCoords(Point2D.Double[] coords) {
		this.coords = coords;
	}

	/**
	 * An abstract method which calculates the area of a figure component.
	 * 
	 * @return An area of a two-dimensional figure.
	 */
	public abstract double calculateArea();

	/**
	 * The method moves geometric figure on a coordinate plane on given
	 * coefficient (shift factor).
	 * 
	 * @param moveCoef
	 *            The shift factor.
	 */
	public void move(int moveCoef) {
		Point2D.Double[] figureCoords = getCoords();
		for (int i = 0; i < figureCoords.length; i++) {
			figureCoords[i].x += moveCoef;
			figureCoords[i].y += moveCoef;
		}
		setCoords(figureCoords);
	}

	/**
	 * The method scales geometric figure on given compression and stretch
	 * coefficient.
	 * 
	 * @param resizeCoef
	 *            Compression and stretch factor.
	 */
	public void resize(float resizeCoef) {
		Point2D.Double[] figureCoords = getCoords();
		for (int i = 0; i < figureCoords.length; i++) {
			figureCoords[i].x *= resizeCoef;
			figureCoords[i].y *= resizeCoef;
		}
		setCoords(figureCoords);
	}

}
