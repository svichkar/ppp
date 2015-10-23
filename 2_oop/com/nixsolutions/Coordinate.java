package com.nixsolutions;


/**Class for keeping coordinates of a point
 * @author maxb
 *
 */
public class Coordinate {

	private float x;
	private float y;

	/**Getter for X coordinate of point
	 * 
	 * @return
	 */
	public float X() {
		return x;
	}

	/**Getter for Y coordinate of point
	 * 
	 * @return
	 */
	public float Y() {
		return y;
	}

	/**Setter for X coordinate
	 * 
	 * @param valX
	 */
	public void setX(float valX) {
		this.x = valX;
	}

	/**Setter for Y coordinate
	 * 
	 * @param valY
	 */
	public void setY(float valY) {
		this.y = valY;
	}
	
	/**Setter for X and Y coordinate 
	 * 
	 * @param valX
	 * @param valY
	 */
	public void setXY (float valX, float valY)
	{
		this.x = valX;
		this.y = valY;
	}

}
