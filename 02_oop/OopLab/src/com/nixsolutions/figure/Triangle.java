package com.nixsolutions.figure;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Random;

/**
 * Triangle extends abstract class Figure and represents triangle shape on the
 * two-dimensional Euclidean plane.
 * 
 * @author baliuga
 * 
 */
public class Triangle extends Figure {
	/**
	 * Default constructor which initializes Triangle object with random
	 * coordinates.
	 */
	public Triangle() {
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
						+ (rangeMax - rangeMin) * randCoords.nextDouble()) };
		setCoords(coords);
	}

	/**
	 * Class constructor specifying Triangle object by two points.
	 * 
	 * @param firstP
	 *            Coordinates of the first vertex of the triangle shape.
	 * @param secondP
	 *            Coordinates of the second vertex of the triangle shape.
	 * @param thirdP
	 *            Coordinates of the third vertex of the triangle shape.
	 */
	public Triangle(Point2D.Double firstP, Point2D.Double secondP,
			Point2D.Double thirdP) {
		Point2D.Double[] coords = { firstP, secondP, thirdP };
		this.setCoords(coords);
	}

	/**
	 * Implementation of the corresponding method from Figure class. Calculates
	 * the area of the square.
	 */
	@Override
	public double calculateArea() {
		Point2D.Double[] triangleCoords = getCoords();
		double firstSide = Math.sqrt(Math.pow(
				(triangleCoords[1].x - triangleCoords[0].x), 2)
				+ Math.pow((triangleCoords[1].y - triangleCoords[0].y), 2));
		double secondSide = Math.sqrt(Math.pow(
				(triangleCoords[2].x - triangleCoords[1].x), 2)
				+ Math.pow((triangleCoords[2].y - triangleCoords[1].y), 2));
		double thirdSide = Math.sqrt(Math.pow(
				(triangleCoords[2].x - triangleCoords[0].x), 2)
				+ Math.pow((triangleCoords[2].y - triangleCoords[0].y), 2));
		double halfPerim = (firstSide + secondSide + thirdSide) / 2;
		return Math.sqrt(halfPerim * (halfPerim - firstSide)
				* (halfPerim - secondSide) * (halfPerim - thirdSide));
	}

	/**
	 * Method converts an Triangle object to its string representation including
	 * area and coordinates.
	 */
	@Override
	public String toString() {
		return "Triangle [Area = " + calculateArea() + ", Coordinates = "
				+ Arrays.toString(getCoords()) + "]";
	}

}
