/**
 * 
 */
package com.nixsolutions.serviceStation;

import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;

/**
 * @author Михаил
 *
 */
public class CreateDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CarDaoImpl car = new CarDaoImpl();
		car.createNewTable();
		// TODO Auto-generated method stub

	}

}
