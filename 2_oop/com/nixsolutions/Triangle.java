package com.nixsolutions;

import java.util.ArrayList;
import java.util.Random;


/**This is inherited class from Shape
 * @author maxb
 * 
 */
public class Triangle extends Shape {

	/**Initializing new Triangle object with random coordinates 
	 * @author maxb
	 */
	public Triangle() {
		Random rnd = new Random();
		// need to add three points
		for (int i = 0; i < 3; i++) {
			Coordinate point = new Coordinate();
			point.setXY(rnd.nextFloat(), rnd.nextFloat());
			this.addCoordinate(point);
		}
	}

	/**Initializing new Triangle object with defined coordinates
	 * @author maxb
	 * @param coordinates Collection of Coordinate objects
	 */
	public Triangle(ArrayList<Coordinate> coordinates) {
		this.addCoordinates(coordinates);
	}

	/**Overriding the base method for getting area
	 * @author maxb
	 */
	@Override
	public float area() {
		// get it by Geron formula
		// get p=(a+b+c)/2
		double a = Helper.getDistance(this.coordinates().get(0), this
				.coordinates().get(1));
		double b = Helper.getDistance(this.coordinates().get(1), this
				.coordinates().get(2));
		double c = Helper.getDistance(this.coordinates().get(0), this
				.coordinates().get(2));

		double p = (a + b + c) / 2;
		return (float) Math.sqrt(p * (p - a) * (p - b) * (p - c));

	}

	/**Overriding the base method for resizing figure
	 * @author maxb
	 */
	@Override
	public void resize(float modifier) {
		if (modifier < 0) {
			System.out
					.print("Modifier cannot be less than 0. Setting it to 1.");
		} else {
			for (Coordinate oneCoordinate : this.coordinates()) {
				// update all coordinates except zero point
				if (this.coordinates().indexOf(oneCoordinate) != 0) {
					oneCoordinate.setX(oneCoordinate.X() * modifier);
					oneCoordinate.setY(oneCoordinate.Y() * modifier);
				}
			}
		}
	}

}
