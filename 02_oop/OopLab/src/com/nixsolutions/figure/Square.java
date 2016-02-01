package com.nixsolutions.figure;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Random;

/**
 * Square extends abstract class Figure and represents square shape on the
 * two-dimensional Euclidean plane.
 * 
 * @author baliuga
 * 
 */
public class Square extends Figure {
	/**
	 * Default constructor which initializes Square object with random
	 * coordinates.
	 */
	public Square() {
		int rangeMin = -10;
		int rangeMax = 10;
		Random randCoords = new Random();
		Point2D.Double[] coords = {
				new Point2D.Double(rangeMin + (rangeMax - rangeMin)
						* randCoords.nextDouble(), rangeMin
						+ (rangeMax - rangeMin) * randCoords.nextDouble()),
				new Point2D.Double(rangeMin + (rangeMax - rangeMin)
						* randCoords.nextDouble(), rangeMin
						+ (rangeMax - rangeMin) * randCoords.nextDouble()),
				new Point2D.Double(rangeMin + (rangeMax - rangeMin)
						* randCoords.nextDouble(), rangeMin
						+ (rangeMax - rangeMin) * randCoords.nextDouble()),
				new Point2D.Double(rangeMin + (rangeMax - rangeMin)
						* randCoords.nextDouble(), rangeMin
						+ (rangeMax - rangeMin) * randCoords.nextDouble()) };
		setCoords(coords);
	}

	/**
	 * Class constructor specifying Square object by two points.
	 * 
	 * @param firstP
	 *            Coordinates of the first vertex of the square shape.
	 * @param secondP
	 *            Coordinates of the second vertex of the square shape.
	 * @param thirdP
	 *            Coordinates of the third vertex of the square shape.
	 * @param fourthP
	 *            Coordinates of the fourth vertex of the square shape.
	 */
	public Square(Point2D.Double firstP, Point2D.Double secondP,
			Point2D.Double thirdP, Point2D.Double fourthP) {
		Point2D.Double[] coords = { firstP, secondP, thirdP, fourthP };
		setCoords(coords);
	}

	/**
	 * Implementation of the corresponding method from Figure class. Calculates
	 * the area of the square.
	 */
	@Override
	public double calculateArea() {
		Point2D.Double[] squareCoords = getCoords();
		return Math.pow((squareCoords[1].x - squareCoords[0].x), 2)
				+ Math.pow((squareCoords[1].y - squareCoords[0].y), 2);
	}

	/**
	 * Method converts an Square object to its string representation including
	 * area and coordinates.
	 */
	@Override
	public String toString() {
		return "Square [Area = " + calculateArea() + ", Coordinates = "
				+ Arrays.toString(getCoords()) + "]";
	}

}
