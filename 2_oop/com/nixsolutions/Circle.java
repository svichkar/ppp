package com.nixsolutions;

import java.util.ArrayList;
import java.util.Random;

public class Circle extends Shape {

	/**Initializing new Circle object with random coordinates 
	 * @author maxb
	 */
	public Circle() {
		Random rnd = new Random();
		// need to add two points
		for (int i = 0; i < 2; i++) {
			Coordinate point = new Coordinate();
			point.setXY(rnd.nextFloat(), rnd.nextFloat());
			this.addCoordinate(point);
		}
	}

	/**Initializing new Circle object with defined coordinates
	 * @author maxb
	 * @param coordinates Collection of Coordinate objects
	 */
	public Circle(ArrayList<Coordinate> coordinates) {
		this.addCoordinates(coordinates);
	}

	/**Overriding the base method for getting area
	 * @author maxb
	 */
	@Override
	public float area() {
		// formula for circle is
		return (float) (Math.PI * Math.pow(this.getRadius(), 2));
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
			// get next area of circle
			double areaNew = this.area() + modifier;
			// get radius size
			double radiusNew = Math.sqrt(areaNew / Math.PI);
			float diff = (float) Math.round(this.getRadius() - radiusNew);
			// updating the second point for circle
			this.coordinates().get(1)
					.setY(this.coordinates().get(1).Y() + diff);
			this.coordinates().get(1).setX(this.coordinates().get(0).X());
		}
	}

	/**Method for getting Radius by using defined coordinates
	 * @author maxb
	 * @return Returns radius of circle
	 */
	public double getRadius() {
		double radius = 0;
		if (this.coordinates().size() > 1) {
			// /get radius length
			radius = Helper.getDistance(this.coordinates().get(0), this
					.coordinates().get(1));
		}
		return radius;
	}

}
