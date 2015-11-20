/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import com.nixsolutions.app.CreateDB;
import com.nixsolutions.app.DropDB;
import com.nixsolutions.app.FillingDB;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class CommonTestsMethods {
	private final static Logger logger = LogManager.getLogger();

	public static void getTables(String tableName) throws Exception {
		Properties properties = ConnectionManager.getProperties();
		// database connection
		Class.forName("org.h2.Driver");
		Connection jdbcConnection = DriverManager.getConnection(properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"));
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// partial database export
		String filePath = "";
		QueryDataSet partialDataSet = new QueryDataSet(connection);
		if (tableName.equalsIgnoreCase("all")) {
			partialDataSet.addTable("car", "select * from sqllab.car");
			partialDataSet.addTable("customer", "select * from sqllab.customer");
			partialDataSet.addTable("worker_specialization", "select * from sqllab.worker_specialization");
			partialDataSet.addTable("worker_status", "select * from sqllab.worker_status");
			partialDataSet.addTable("worker", "select * from sqllab.worker");
			partialDataSet.addTable("order_in_work", "select * from sqllab.order_in_work");
			partialDataSet.addTable("order_status", "select * from sqllab.order_status");
			partialDataSet.addTable("part", "select * from sqllab.part");
			partialDataSet.addTable("part_order", "select * from sqllab.part_order");
			partialDataSet.addTable("order_worker", "select * from sqllab.order_worker");
			filePath = "src/test/resources/dataset.xml";
		} else {
			partialDataSet.addTable(tableName, "select * from sqllab." + tableName);
			filePath = "src/test/resources/" + tableName + "/" + tableName + ".xml";
		}
		FlatXmlDataSet.write(partialDataSet, new FileOutputStream(filePath));
		logger.info("all tables were migrated to " + filePath);

	}

	public static void deleteDB() throws SQLException, ClassNotFoundException {
		DropDB.dropDB();
	}

	public static void createDB() throws SQLException, ClassNotFoundException {
		CreateDB.createDB();
	}

	public static void fillingDB() throws SQLException, ClassNotFoundException {
		FillingDB.fillingDB();
	}

	public static void updateDB() throws SQLException, ClassNotFoundException {
		deleteDB();
		createDB();
		fillingDB();
	}
}