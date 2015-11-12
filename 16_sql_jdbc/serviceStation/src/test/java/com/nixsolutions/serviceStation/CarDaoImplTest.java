package com.nixsolutions.serviceStation;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.dataset.xml.FlatXmlWriter;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CarDaoImplTest extends TestCase {
	private final static Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		Properties properties = DbConnector.getProperties();
		// database connection
		Class.forName("org.h2.Driver");
		Connection jdbcConnection = DriverManager.getConnection(properties.getProperty("h2URL"),
				properties.getProperty("h2Login"), properties.getProperty("h2Password"));
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// partial database export
		QueryDataSet partialDataSet = new QueryDataSet(connection);
		partialDataSet.addTable("sqllab.car","SELECT * FROM sqllab.car");
		partialDataSet.addTable("sqllab.customer");
		partialDataSet.addTable("sqllab.worker_specialization");
		partialDataSet.addTable("sqllab.worker_status");
		partialDataSet.addTable("sqllab.worker");
		partialDataSet.addTable("sqllab.order_in_work");
		partialDataSet.addTable("sqllab.order_status");
		partialDataSet.addTable("sqllab.part");
		partialDataSet.addTable("sqllab.part_order");
		partialDataSet.addTable("sqllab.order_worker");
		FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/dataset.xml"));
		logger.info("all tables were migrated to  src/test/resources/dataset.xml");

	}

}
