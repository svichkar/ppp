package com.nixsolutions.figure;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Random;

/**
 * Circle extends abstract class Figure and represents circle on the
 * two-dimensional Euclidean plane.
 * 
 * @author baliuga
 * 
 */
public class Circle extends Figure {
	/**
	 * Default constructor which initializes Circle object with random
	 * coordinates.
	 */
	public Circle() {
		int rangeMin = -10;
		int rangeMax = 10;
		Random randCoords = new Random();
		Point2D.Double[] coords = {
				new Point2D.Double(rangeMin + (rangeMax - rangeMin)
						* randCoords.nextDouble(), rangeMin
						+ (rangeMax - rangeMin) * randCoords.nextDouble()),
				new Point2D.Double(rangeMin + (rangeMax - rangeMin)
						* randCoords.nextDouble(), rangeMin
						+ (rangeMax - rangeMin) * randCoords.nextDouble()), };
		setCoords(coords);
	}

	/**
	 * Class constructor specifying Circle object by two points.
	 * 
	 * @param firstP
	 *            Coordinates of center of the circle.
	 * @param secondP
	 *            Coordinates of the point on the circle circumference.
	 */
	public Circle(Point2D.Double firstP, Point2D.Double secondP) {
		Point2D.Double[] coords = { firstP, secondP };
		this.setCoords(coords);
	}

	/**
	 * The method scales circle on given coefficient. Overrides the
	 * corresponding method from Figure class.
	 */
	@Override
	public void resize(float resizeCoef) {
		Point2D.Double[] figureCoords = getCoords();
		for (int i = 0; i < figureCoords.length; i++) {
			figureCoords[i].x *= Math.sqrt(resizeCoef);
			figureCoords[i].y *= Math.sqrt(resizeCoef);
		}
		setCoords(figureCoords);
	}

	/**
	 * Implementation of the corresponding method from Figure class. Calculates
	 * the area of the circle.
	 */
	@Override
	public double calculateArea() {
		Point2D.Double[] circleCoords = getCoords();
		return Math.PI
				* (Math.pow((circleCoords[1].x - circleCoords[0].x), 2) + Math
						.pow((circleCoords[1].y - circleCoords[0].y), 2));
	}

	/**
	 * Method converts an Circle object to its string representation including
	 * area and coordinates.
	 */
	@Override
	public String toString() {
		return "Circle [Area = " + calculateArea() + ", Coordinates = "
				+ Arrays.toString(getCoords()) + "]";
	}

}
