package com.nixsolutions;

import java.awt.Color;
import java.util.Comparator;

/**
 * Class represents Car
 * 
 * @author maxb
 * 
 */
public class Car {

	private String nameOfCar;
	private int powerInHorses;
	private Color color;

	/**Default constractor
	 * 
	 */
	public Car() {

	}

	public Car(String name) {
		this.setName(name);
	}

	/**Method provides name of car
	 * 
	 * @return
	 */
	public String name() {
		return nameOfCar;
	}

	/**Method defines name of car
	 * 
	 * @param nameOfCar
	 */
	public void setName(String nameOfCar) {
		this.nameOfCar = nameOfCar;
	}

	/**Method provides hp of car
	 * 
	 * @return
	 */
	public int power() {
		return powerInHorses;
	}

	/**Method defines hp of car
	 * 
	 * @param powerInHorses Define hp value as type int
	 */
	public void setPower(int powerInHorses) {
		this.powerInHorses = powerInHorses;
	}

	/**Method provides color of car 
	 * 
	 * @return
	 */
	public Color color() {
		return color;
	}

	/**Method defines color of car
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public boolean equals(Object otherCar) {
		if (otherCar != null & otherCar instanceof Car) {
			Car oCar = (Car) otherCar;
			if (this.name().compareTo(oCar.name()) == 0) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		return (this.name() + this.power()).hashCode();
	}

	public String toString() {
		return this.name();
	}

}
