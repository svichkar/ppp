/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.sql.SQLException;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;

/**
 * @author mixeyes
 *
 */
public class DaoInWork {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	   	DbConnector connector = null;
			try {
				connector = new DbConnector();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	CarDaoImpl carDaoImpl = new CarDaoImpl(connector.getConnection());
	    	carDaoImpl.getAllCar();

	}

}
