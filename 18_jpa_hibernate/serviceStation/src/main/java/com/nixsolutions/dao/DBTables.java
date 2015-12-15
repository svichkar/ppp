package com.nixsolutions.dao;

import java.sql.SQLException;

public interface DBTables {

	/** create new Customer table 
	 * @throws SQLException */
	public void createNewTable() ;

	/** delete Table With All Data 
	 * @throws SQLException */
	public void deleteTableWithAllData();

}
