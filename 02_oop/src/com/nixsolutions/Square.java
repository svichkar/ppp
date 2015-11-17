package com.nixsolutions;

import java.awt.Point;

/**
 * The Class Square.
 */
public class Square extends FeometricFigure {

	/**
	 * Constructor for class square.
	 */
	public Square() {
		points[0] = new Point(8, 0);
		points[1] = new Point(10, 8);
		points[2] = new Point(2, 10);
		points[3] = new Point(0, 2);
	}

	/** The massive of coordinates of the figure */
	Point[] points = new Point[4];

	@Override
	public void moving(Point dots) {
		points[0].x = points[0].x + dots.x;
		points[0].y = points[0].y + dots.y;
		points[1].x = points[0].x + dots.x;
		points[1].y = points[0].y + dots.y;
		points[2].x = points[0].x + dots.x;
		points[2].y = points[0].y + dots.y;
		points[3].x = points[0].x + dots.x;
		points[4].y = points[0].y + dots.y;

	}

	@Override
	public double area() {
		double side = Math.sqrt(Math.pow(points[1].x - points[0].x, 2) + Math.pow(points[1].y - points[0].y, 2));
		return Math.pow(side, 2);
	}

	@Override
	public double changeSize(double coefficient) {
		double changeArea = area() * coefficient;
		return changeArea;
	}

	@Override
	public String nameOfFigure() {
		String nameFigure = "Square";
		return nameFigure;
	}

}
