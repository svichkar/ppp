package com.nixsolutions;

import java.util.Random;

public class Triangle extends Shape {

	/**area method is overriden method from Shape class.
	 * It is calculated as a square root of 3 divided by 4 multiplied by square of size field.
	 * @author	kulishov
	 * @return	Returns float value of area. */
	@Override
	public float area() {
		return (float) (Math.sqrt(3) * Math.pow(getSize(), 2) / 4d);
	}
	
	/**Initializes new Triangle object with random coordinates and size.
	 * @author	kulishov */
	public Triangle(){
		Random rand = new Random();
		setSize(rand.nextFloat());
		setCoordX(rand.nextInt());
		setCoordY(rand.nextInt());
	}
	
	/**Initializes new Triangle object with defined parameters.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate,
	 * @param	size	Defines size. */
	public Triangle(int coordX, int coordY, float size){
		setSize(size);
		setCoordX(coordX);
		setCoordY(coordY);
	}
	
	/**Initializes new Triangle object with defined coordinates and random size.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate. */
	public Triangle(int coordX, int coordY){
		Random rand = new Random();
		setSize(rand.nextFloat());
		setCoordX(coordX);
		setCoordY(coordY);
	}
	
	/**Initializes new Triangle object with defined size and random coordinates.
	 * @author	kulishov
	 * @param	size	Defines size. */
	public Triangle(float size){
		Random rand = new Random();
		setSize(size);
		setCoordX(rand.nextInt());
		setCoordY(rand.nextInt());
	}
}
