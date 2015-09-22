package com.nixsolutions;

import java.util.Random;

public class Triangle extends Shape {
	
	/**Initializes new Triangle object with random coordinates and size.
	 * @author	kulishov */
	public Triangle(){
		Random rand = new Random();
		setCoordinates(new float[][]{
			{rand.nextFloat(), rand.nextFloat()},
			{rand.nextFloat(), rand.nextFloat()},
			{rand.nextFloat(), rand.nextFloat()},
			});
	}
	
	/**Initializes new Triangle object with defined parameters.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate,
	 * @param	size	Defines size. */
	public Triangle(float[][] coordinates){
		setCoordinates(coordinates);
	}
	
	/**changeSize method is implementation of method from Shape class.
	 * It is used to change side size values.
	 * @author	kulishov
	 * @param	modifier	Parameter of type 'float'. 
	 * 						Defines value of modifier by which side is multiplied. */
	public void changeSize(float modifier){
		if (modifier < 0){
			System.out.println("Modifier cannot be less than zero. Setting modifier to 1.");
		} else {
			float [][] newCoords = getCoordinates();
			for (int i = 0; i < newCoords.length; i++){
				for (int j = 0; j < newCoords[i].length; j++){
					newCoords[i][j] *= modifier;
				}
			}
			setCoordinates(newCoords);
		}
	}
	
	/**area method is implementation of method from Shape class.
	 * It is calculated by Heron's formula.
	 * @author	kulishov
	 * @return	Returns float value of area. */
	@Override
	public float area() {
		float sideA = (float) Math.sqrt(
				Math.pow(getCoordinates()[0][0] - getCoordinates()[1][0], 2) + 
				Math.pow(getCoordinates()[0][1] - getCoordinates()[1][1], 2));
		float sideB = (float) Math.sqrt(
				Math.pow(getCoordinates()[1][0] - getCoordinates()[2][0], 2) + 
				Math.pow(getCoordinates()[1][1] - getCoordinates()[2][1], 2));
		float sideC = (float) Math.sqrt(
				Math.pow(getCoordinates()[2][0] - getCoordinates()[0][0], 2) + 
				Math.pow(getCoordinates()[2][1] - getCoordinates()[0][1], 2));
		float halfPerimeter = (sideA + sideB + sideC) / 2;
		return (float) Math.sqrt(halfPerimeter * (halfPerimeter - sideA) * 
				(halfPerimeter - sideB) * (halfPerimeter - sideC));
	}
}
