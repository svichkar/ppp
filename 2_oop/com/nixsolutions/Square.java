package com.nixsolutions;

import java.util.ArrayList;
import java.util.Random;

/**This is inherited class from Shape
 * 
 * @author maxb
 *
 */
public class Square extends Shape {
	
	/**Initializing new Square object with random coordinates 
	 * @author maxb
	 */
	public Square() {
		Random rnd = new Random();
		// need to add four points
		Coordinate point0 = new Coordinate();
		point0.setXY(rnd.nextFloat(), rnd.nextFloat());
		this.addCoordinate(point0);

		Coordinate point1 = new Coordinate();
		point1.setXY(rnd.nextFloat(), rnd.nextFloat());
		this.addCoordinate(point1);

		Coordinate point2 = new Coordinate();
		point2.setXY(point1.X() + this.side(), point1.Y());
		this.addCoordinate(point2);

		Coordinate point3 = new Coordinate();
		point3.setXY(point0.X() + this.side(), point0.Y());
		this.addCoordinate(point3);
	}

	/**Initializing new Square object with defined coordinates
	 * @author maxb
	 * @param coordinates Collection of Coordinate objects
	 */
	public Square(ArrayList<Coordinate> coordinates) {
		this.addCoordinates(coordinates);
	}

	/**Overriding the base method for getting area
	 * @author maxb
	 */
	@Override
	public float area() {
		//
		return (float) Math.pow(this.side(), 2);
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
			
			double areaNew = this.area() * modifier;
			double sideNew = Math.sqrt(areaNew);
			// /get diff of side
			float diff = (float) Math.round(this.side() - sideNew);
			// /updating coordinates for points that are close to zero point
			this.coordinates().get(1)
					.setX(this.coordinates().get(1).X() + diff);
			this.coordinates().get(3)
					.setY(this.coordinates().get(2).Y() + diff);
			// /updating point that is opposite to zero point
			this.coordinates().get(2).setX(this.coordinates().get(1).X());
			this.coordinates().get(2).setY(this.coordinates().get(3).Y());
		}
	}

	/**Method allows to get a size of side of square 
	 * @author maxb
	 * @return Returns float value 
	 */
	public float side() {
		float side = Helper.getDistance(this.coordinates().get(0), this
				.coordinates().get(1));
		return side;
	}

}
