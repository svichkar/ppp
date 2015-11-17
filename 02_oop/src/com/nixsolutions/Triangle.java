package com.nixsolutions;

import java.awt.Point;

/**
 * The Class Triangle.
 */
public class Triangle extends FeometricFigure {

	/**
	 * Constructor for class triangle.
	 */
	public Triangle() {
		points[0] = new Point(2, -3);
		points[1] = new Point(1, 1);
		points[2] = new Point(-6, 5);
	}

	/** Coordinates of the center of the Triangle */
	Point[] points = new Point[3];

	@Override
	public void moving(Point dots) {
		points[0].x = points[0].x + dots.x;
		points[0].y = points[0].y + dots.y;

		points[1].x = points[0].x + dots.x;
		points[1].y = points[0].y + dots.y;

		points[2].x = points[0].x + dots.x;
		points[2].y = points[0].y + dots.y;

	}

	@Override
	public double area() {
		double area = Math.sqrt((points[0].x - points[2].x) * (points[1].y - points[2].y)
				- ((points[1].x) - points[2].x) * (points[0].y - points[2].y));
		return area;
	}

	@Override
	public double changeSize(double coefficient) {
		double changeArea = area() * coefficient;
		return changeArea;

	}

	@Override
	public String nameOfFigure() {
		String nameFigure = "Triangle";
		return nameFigure;
	}

}
