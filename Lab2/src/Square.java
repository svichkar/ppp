import java.awt.Point;

/**
 * Class for geometric shape - square
 * 
 * @author Ivan Usanin
 *
 */
public class Square extends Figure {
	Square() {
		points[0] = new Point(1, 1);
		points[1] = new Point(1, 40);
		points[2] = new Point(40, 40);
		points[3] = new Point(40, 1);
	}

	/**
	 * The coordinates of the corners of a square
	 */
	private Point[] points = new Point[4];

	@Override
	public void moveOn(Point increment) {
		for (Point point : points) {
			point.x += increment.x;
			point.y += increment.y;
		}
	}

	@Override
	public double area() {
		int side = points[1].y - points[0].y;
		double area = side * side;
		return area;
	}

	@Override
	public void changeSize(float coef) {
		double newArea = area() * coef;
		int newSide = (int) Math.sqrt(newArea);
		points[1].y = points[0].y + newSide;
		points[2].x = points[0].x + newSide;
		points[2].y = points[1].y;
		points[3].x = points[2].x;
	}
}
