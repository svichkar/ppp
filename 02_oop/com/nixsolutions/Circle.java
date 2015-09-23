package com.nixsolutions;

import java.util.Random;

public class Circle extends Shape {

	private float radius;
	
	/**getRadius method is used to get the value of radius field.
	 * @author	kulishov 
	 * @return	Returns float value of radius. */
	public float getRadius() {
		return radius;
	}
	
	/**setRadius method is used to set the value of radius field.<p>
	 * Please note that radius of a Circle cannot be less than zero.
	 * If negative value is sent as parameter - radius value 
	 * will be set to zero and warning message will be displayed in the console.
	 * @author	kulishov 
	 * @param	value	Intended value of the size. */
	private void setRadius(float value) {
		if (value < 0) {
			radius = 0;
			System.out.println("Radius cannot be less than zero. Setting Radius to 0.");
		} else {
			radius = value;
		}
	}
	
	/**Initializes new Circle object with random coordinates and size.
	 * @author	kulishov */
	public Circle() {
		Random rand = new Random();
		setRadius(rand.nextFloat());
		setCoordinates(new float[][]{{rand.nextFloat(), rand.nextFloat()}});
	}
	
	/**Initializes new Circle object with defined parameters.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate,
	 * @param	size	Defines size. */
	public Circle(float coordX, float coordY, float radius) {
		setRadius(radius);
		setCoordinates(new float[][]{{coordX, coordY}});
	}
	
	/**Initializes new Circle object with defined coordinates and random size.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate. */
	public Circle(float coordX, float coordY) {
		Random rand = new Random();
		setRadius(rand.nextFloat());
		setCoordinates(new float[][]{{coordX, coordY}});
	}
	
	/**Initializes new Circle object with defined size and random coordinates.
	 * @author	kulishov
	 * @param	size	Defines size. */
	public Circle(float radius) {
		Random rand = new Random();
		setRadius(radius);
		setCoordinates(new float[][]{{rand.nextFloat(), rand.nextFloat()}});
	}
	
	/**area method is implementation of method from Shape class.
	 * It is calculated as a PI multiplied by square of radius field.
	 * @author	kulishov
	 * @return	Returns float value of area. */
	public float area() {
		return (float) (Math.PI * Math.pow(getRadius(), 2));
	}
	
	/**changeSize method is implementation of method from Shape class.
	 * It is used to change radius field value.
	 * @author	kulishov
	 * @param	modifier	Parameter of type 'float'. 
	 * 						Defines value of modifier by which radius is multiplied. */
	public void changeSize(float modifier) {
		setRadius(getRadius() * modifier);
	}
}
