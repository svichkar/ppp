import java.awt.Point;

/**Overall class for geometric shape
 * @author Ivan Usanin
 *
 */
public abstract class Figure {
	/**
	 * The method for moving figure. Object coordinates will be incremented by a predetermined amount
	 * @param increment - increment by X and Y
	 */
	public abstract void moveOn(Point increment);

	/**
	 * Method will calculate area of Figure
	 * @return area of figure
	 */
	public abstract double area();

	/**
	 * The method will change figure size. Object area will change according input coefficient
	 * @param coef - resize area coefficient
	 */
	public abstract void changeSize(float coef);
}
