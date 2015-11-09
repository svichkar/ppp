package com.nixsolutions.serviceStation.dbCommon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public interface DBTables {

	/** create new Customer table */
	public void createNewTable();

	/** delete Table With All Data */
	public void deleteTableWithAllData();

}
