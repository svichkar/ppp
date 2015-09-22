package com.nixsolutions;

import java.util.Random;

public class Square extends Shape {
	
	private float side;
	
	/**getSide method is used to get the value of side field.
	 * @author	kulishov 
	 * @return	Returns float value of side. */
	public float getSide(){
		return side;
	}
	
	/**setSide method is used to set the value of side field.<p>
	 * Please note that side of a Square cannot be less than zero.
	 * If negative value is sent as parameter - side value 
	 * will be set to zero and warning message will be displayed in the console.
	 * @author	kulishov 
	 * @param	value	Intended value of the size. */
	private void setSide(float value){
		if (value < 0){
			side = 0;
			System.out.println("Radius cannot be less than zero. Setting Radius to 0.");
		} else {
			side = value;
		}
	}
	
	/**Initializes new Square object with random coordinates and size.
	 * @author	kulishov */
	public Square(){
		Random rand = new Random();
		setSide(rand.nextFloat());
		setCoordinates(generateSquareCoords(rand.nextFloat(), rand.nextFloat(), getSide()));
	}
	
	/**Initializes new Square object with defined parameters.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate.
	 * @param	size	Defines size. */
	public Square(float coordX, float coordY, float side){
		setSide(side);
		setCoordinates(generateSquareCoords(coordX, coordY, getSide()));
	}
	
	/**Initializes new Square object with defined coordinates and random size.
	 * @author	kulishov
	 * @param	coordX	Defines X coordinate.
	 * @param	coordY	Defines Y coordinate. */
	public Square(float coordX, float coordY){
		Random rand = new Random();
		setSide(rand.nextFloat());
		setCoordinates(generateSquareCoords(coordX, coordY, getSide()));
	}
	
	/**Initializes new Square object with defined size and random coordinates.
	 * @author	kulishov
	 * @param	size	Defines size. */
	public Square(float side){
		Random rand = new Random();
		setSide(side);
		setCoordinates(generateSquareCoords(rand.nextFloat(), rand.nextFloat(), getSide()));
	}
	
	/**area method is overriden method from Shape class.
	 * It is calculated as a square of size field.
	 * @author	kulishov
	 * @return	Returns float value of area. */
	public float area() {
		return (float) Math.pow(getSide(), 2);
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
			setSide(getSide() * modifier);
		}
	}
	
	/**generateSquareCoords is used to generate Square coordinates by defined parameters.
	 * @author	kulishov
	 * @param	startX	Defines X coordinate of the left bottom corner of the Square. 
	 * @param	startY	Defines Y coordinate of the left bottom corner of the Square.
	 * @param	side	Defines side of the Square.
	 * @return			Returns generated coordinates. */
	private float[][] generateSquareCoords(float startX, float startY, float side){
		return new float[][]{
			{startX, startY},
			{startX + side, startY},
			{startX, startY + side},
			{startX + side, startY + side}
			};
	}
}
