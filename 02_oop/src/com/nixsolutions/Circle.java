package com.nixsolutions;

import java.awt.Point;

/**
 * The Class Circle.
 */
public class Circle extends FeometricFigure {

	/**
	 * Constructor for class circle.
	 */
	public Circle() {
		points[0] = new Point(1, 1);
		radius = 25;
	}

	/** Coordinates of the center of the circle */
	Point[] points = new Point[1];

	/** The radius of circle */
	private int radius;

	/** The constant pi. */
	private final double pi = 3.14;

	@Override
	public void moving(Point dots) {
		points[0].x = points[0].x + dots.x;
		points[0].y = points[0].y + dots.y;

	}

	@Override
	public double area() {
		return pi * Math.pow(radius, 2);
	}

	@Override
	public double changeSize(double coefficient) {
		double changeArea = area() * coefficient;
		return changeArea;

	}

	@Override
	public String nameOfFigure() {
		String nameFigure = "Circle";
		return nameFigure;
	}

}
