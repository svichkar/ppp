import java.awt.Point;

/**
 * Class for geometric shape - circle
 * 
 * @author Ivan Usanin
 *
 */
public class Circle extends Figure {
	Circle() {
		point = new Point(1, 1);
		radius = 50;
	}

	private static final double pi = 3.14;
	/**
	 * Coordinate of the center of the circle
	 */
	private Point point;
	/**
	 * Size of radius
	 */
	private int radius;

	@Override
	public void moveOn(Point increment) {
		point.x = point.x + increment.x;
		point.y = point.y + increment.y;
	}

	@Override
	public double area() {
		double square = pi * (radius * radius);
		return square;
	}

	@Override
	public void changeSize(float coef) {
		double newArea = area() * coef;
		radius = (int) Math.sqrt(newArea / pi);
	}

}
