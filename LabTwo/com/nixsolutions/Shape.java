package com.nixsolutions;

/**Shape class is an abstract class created for the "OOP" quiz.
 * coordX and coordY fields define the location of figure's center,
 * while size field provides info about definitive characteristic of the figure.
 * @author	kulishov */
public abstract class Shape {
	
	private float[][] coordinates;
	
	/**setCoordinates method is used to set the value of coordinates field.
	 * @author	kulishov 
	 * @param	value	Intended value of the coordinates. */
	protected void setCoordinates(float[][] value){
		coordinates = value;
	}
	
	/**getCoordX method is used to get the value of coordX field.
	 * @author	kulishov 
	 * @return	Returns integer value of coordX. */
	public float[][] getCoordinates(){
		return coordinates;
	}
	
	/**move method is used to change the position of the figure.
	 * @author	kulishov
	 * @param	diffX	Defines the difference between current and desired positions by X axis.
	 * @param	diffY	Defines the difference between current and desired positions by Y axis. */
	public void move(float diffX, float diffY){
		for (int i = 0; i < coordinates.length; i++){
			coordinates[i][0] += diffX;
			coordinates[i][1] += diffY;
		}
	}
	
	/**area method is an abstract method that should return the area value for the Shape.
	 * @author	kulishov
	 * @return	Returns float value of the area of the Shape. */
	public abstract float area();	

	/**changeSize method is used to change size field value.
	 * @author	kulishov
	 * @param	modifier	Parameter of type 'float'. 
	 * 						Defines value of modifier by which size is multiplied. */
	public abstract void changeSize(float modifier);
}
