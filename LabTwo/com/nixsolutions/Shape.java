package com.nixsolutions;

/**Shape class is an abstract class created for the "OOP" quiz.
 * coordX and coordY fields define the location of figure's center,
 * while size field provides info about definitive characteristic of the figure.
 * @author	kulishov */
public abstract class Shape {
	
	private int coordX;
	private int coordY;
	private float size;
	
	/**setCoordX method is used to set the value of coordX field.
	 * @author	kulishov 
	 * @param	value	Intended value of the coordX. */
	protected void setCoordX(int value){
		coordX = value;
	}
	
	/**setCoordY method is used to set the value of coordY field.
	 * @author	kulishov
	 * @param	value	Intended value of the coordY. */
	protected void setCoordY(int value){
		coordY = value;
	}
	
	/**setSize method is used to set the value of size field.<p>
	 * Please note that size of a Shape cannot be less than zero.
	 * If negative value is sent as parameter - size value 
	 * will be set to zero and warning message will be displayed in the console.
	 * @author	kulishov 
	 * @param	value	Intended value of the size. */
	protected void setSize(float value){
		if (value < 0){
			size = 0;
			System.out.println("Size cannot be less than zero. Setting shape size to 0.");
		} else {
			size = value;
		}
	}
	
	/**getCoordX method is used to get the value of coordX field.
	 * @author	kulishov 
	 * @return	Returns integer value of coordX. */
	public int getCoordX(){
		return coordX;
	}
	
	/**getCoordY method is used to get the value of coordY field.
	 * @author	kulishov 
	 * @return	Returns integer value of coordY. */
	public int getCoordY(){
		return coordY;
	}
	
	/**getSize method is used to get the value of size field.
	 * @author	kulishov 
	 * @return	Returns float value of size. */
	public float getSize(){
		return size;
	}
	
	/**changeSize method is used to change size field value.
	 * @author	kulishov
	 * @param	modifier	Parameter of type 'float'. 
	 * 						Defines value of modifier by which size is multiplied.
	 * 						Has internal call to the setSize method. */
	public void changeSize(float modifier){
		setSize(getSize() * modifier);
	}
	
	/**move method is used to change the position of the figure.
	 * @author	kulishov
	 * @param	diffX	Defines the difference between current and desired positions by X axis.
	 * @param	diffY	Defines the difference between current and desired positions by Y axis. */
	public void move(int diffX, int diffY){
		setCoordX(getCoordX() + diffX);
		setCoordY(getCoordY() + diffY);
	}
	
	/**area method is an abstract method that should return the area value for the Shape.
	 * @author	kulishov
	 * @return	Returns float value of the area of the Shape. */
	public abstract float area();
}
