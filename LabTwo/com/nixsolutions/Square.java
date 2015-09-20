package com.nixsolutions;

import java.util.Random;

public class Square extends Shape {

	/**area method is overriden method from Shape class.
	 * It is calculated as a square of size field.
	 * @author	kulishov
	 * @return	Returns float value of area. */
	@Override
	public float area() {
		return (float) Math.pow(getSize(), 2);
	}
	
	/**Initializes new Square object with random coordinates and size.
	 * @author	kulishov */
	public Square(){
		Random rand = new Random();
		setSize(rand.nextFloat());
		setCoordX(rand.nextInt());
		setCoordY(rand.nextInt());
	}
	
	/**Initializes new Square object with defined parameters.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate,
	 * @param	size	Defines size. */
	public Square(int coordX, int coordY, float size){
		setSize(size);
		setCoordX(coordX);
		setCoordY(coordY);
	}
	
	/**Initializes new Square object with defined coordinates and random size.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate. */
	public Square(int coordX, int coordY){
		Random rand = new Random();
		setSize(rand.nextFloat());
		setCoordX(coordX);
		setCoordY(coordY);
	}
	
	/**Initializes new Square object with defined size and random coordinates.
	 * @author	kulishov
	 * @param	size	Defines size. */
	public Square(float size){
		Random rand = new Random();
		setSize(size);
		setCoordX(rand.nextInt());
		setCoordY(rand.nextInt());
	}
}
