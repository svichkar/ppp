package com.nixsolutions;

import java.util.ArrayList;


/**This is base class. 
 * @author maxb
 *
 */
public abstract class Shape {

	private ArrayList<Coordinate> coordinate = new ArrayList<Coordinate>();

	/**This method for providing collection of coordinates
	 * @author maxb
	 * @return Return collection of coordinates
	 */
	public ArrayList<Coordinate> coordinates() {
		return this.coordinate;
	}

	/**This method for adding one coordinate
	 * @author maxb
	 * @param xy Coordinate that has to contain X and Y coordinates
	 */
	public void addCoordinate(Coordinate xy) {
		this.coordinate.add(xy);
	}

	/**This method allows to add collection of coordinates
	 * 
	 * @param allCoordinates
	 */
	public void addCoordinates(ArrayList<Coordinate> allCoordinates) {
		this.coordinate.clear();
		this.coordinate.addAll(allCoordinates);
	}

	/**This method allows us to relocate object on defined distance
	 * @author maxb
	 * @param x Define the diff between current and next position on X axis
	 * @param y Define the diff between current and next position on Y axis
	 */
	public void relocate(float x, float y) {
		for (Coordinate coo : this.coordinate) {
			coo.setX(coo.X() + x);
			coo.setY(coo.Y() + y);
		}
	}

	/**This method calculates an area of figure
	 * @author maxb
	 * @return - Area of figure
	 */
	public abstract float area();

	/**This method allows to change a size of object by multiplying on modifier 
	 * @author maxb
	 * @param modifier - Parameter of float type
	 */
	public abstract void resize(float modifier);
}
