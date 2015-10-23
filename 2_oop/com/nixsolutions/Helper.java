package com.nixsolutions;

import java.util.ArrayList;


/**Class that contains auxiliary methods 
 * @author maxb
 *
 */
public class Helper {

	/**Method allows to get distance between two point
	 * 
	 * @param c1 Coordinates of first point
	 * @param c2 Coordinates of second point
	 * @return  Returns distance
	 */
	public static float getDistance(Coordinate c1, Coordinate c2) {
		return (float) Math.sqrt(Math.pow(c1.X() - c2.X(), 2)
				+ Math.pow(c1.Y() - c2.Y(), 2));
	}

}
