import java.awt.Point;

/**
 * Class for geometric shape - isosceles triangle
 * 
 * @author Ivan Usanin
 *
 */
public class Triangle extends Figure {
	Triangle() {
		points[0] = new Point(1, 1);
		points[1] = new Point(30, 50);
		points[2] = new Point(50, 1);
	}
	/**
	 * The coordinates of the corners of a triangle
	 */
	private Point[] points = new Point[3];

	@Override
	public void moveOn(Point increment) {
		for (Point point : points) {
			point.x += increment.x;
			point.y += increment.y;
		}
	}

	@Override
	public double area() {
		int a = points[2].x - points[0].x;
		int h = points[1].y - points[0].y;
		double area = (a * h) / 2.0;
		return area;
	}

	@Override
	public void changeSize(float coef) {
		double newArea = area() * coef;
		double coefSimilarity = Math.sqrt(area() / newArea);
		double newBase = (points[2].x - points[0].x) / coefSimilarity;
		points[2].x = (int) (points[0].x + newBase);
		Point midleBase = new Point(points[0].x + ((int) (newBase / 2)), points[0].y);
		double newHeight = (newArea * 2) / newBase;
		points[1].x = midleBase.x;
		points[1].y = (int) (points[0].y + newHeight);
	}
}
