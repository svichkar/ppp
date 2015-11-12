/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.ServiceFactory;

/**
 * @author mixeyes
 *
 */
public class CommonTestsMethods {
	private final static Logger logger = LogManager.getLogger();

	public static void getTables(String tableName) throws Exception {
		Properties properties = DbConnector.getProperties();
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
			filePath = "src/test/resources/" + tableName + ".xml";
		}
		FlatXmlDataSet.write(partialDataSet, new FileOutputStream(filePath));
		logger.info("all tables were migrated to  src/test/resources/dataset.xml");

	}

	public static void deleteDB() throws SQLException {
		DropDB.dropDB();
	}

	public static void createDB() throws SQLException {
		CreateDB.createDB();
	}
	
	public static void fillingDB() throws SQLException {
		FillingDB.main(null);
	}
	
	public static void updateDB() throws SQLException {
		deleteDB();
		createDB();
		fillingDB();
	}
}
