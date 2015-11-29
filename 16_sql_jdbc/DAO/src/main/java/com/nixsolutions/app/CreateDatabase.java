package com.nixsolutions.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateDatabase {

	private final static Logger LOG = LogManager
			.getLogger(CreateDatabase.class);
	private static Properties props;
	private static Connection conn;
	private static Statement stat;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		@SuppressWarnings("rawtypes")
		Class cls = Class.forName("org.h2.Driver");
		props = new Properties();
		// get path to properties file
		ClassLoader cloader = cls.getClass().getClassLoader();
		String fileWithSettings = cloader.getResource("jdbc.properties")
				.getFile();

		try (FileInputStream fs = new FileInputStream(fileWithSettings)) {
			props.load(fs);
		} catch (IOException ex) {
			LOG.debug(ex, ex);
		}

		try {
			conn = DriverManager.getConnection(
					props.getProperty("CONNECTION_STRING"),
					props.getProperty("USER_ID"),
					props.getProperty("USER_PASSWORD"));
			stat = conn.createStatement();
			stat.addBatch("CREATE SCHEMA sqllab;");
			stat.addBatch("SET SCHEMA sqllab;");
			stat.addBatch("CREATE TABLE car (" + "car_id bigint IDENTITY,"
					+ "model VARCHAR(256) NOT NULL,"
					+ "vin VARCHAR(256) NOT NULL, "
					+ "description VARCHAR(256));");
			stat.addBatch("CREATE TABLE customer ("
					+ "customer_id bigint IDENTITY,	"
					+ "f_name VARCHAR(256) NOT NULL,"
					+ "l_name VARCHAR(256) NOT NULL," + "phone VARCHAR(30));");
			stat.addBatch("CREATE TABLE status ("
					+ "status_id tinyint IDENTITY, "
					+ "status_name VARCHAR(256) NOT NULL);");
			stat.addBatch("CREATE TABLE part (	"
					+ "part_id bigint IDENTITY,	"
					+ "part_name VARCHAR(256) NOT NULL,	"
					+ "vendor VARCHAR(256) NOT NULL,	"
					+ "amount bigint NOT NULL);");
			stat.addBatch("CREATE TABLE worker (	"
					+ "worker_id bigint IDENTITY,	"
					+ "f_name VARCHAR(256) NOT NULL,	"
					+ "l_name VARCHAR(256) NOT NULL,);");
			stat.addBatch("CREATE TABLE worker_specification(	"
					+ "spec_id bigint IDENTITY,	"
					+ "spec_name VARCHAR(256) NOT NULL);");
			stat.addBatch("CREATE TABLE order_status(	"
					+ "order_status_id tinyint IDENTITY,	"
					+ "order_status_name VARCHAR(256) NOT NULL);");
			stat.addBatch("CREATE TABLE order_in_work (	"
					+ "order_id bigint IDENTITY,	"
					+ "description VARCHAR(256),	"
					+ "datetime_start TIMESTAMP NOT NULL,	"
					+ "datetime_end TIMESTAMP);");
			stat.addBatch("CREATE TABLE order_worker(	"
					+ "worker_id bigint,	"
					+ "order_id bigint,	"
					+ "completed BOOLEAN);");
			stat.addBatch("CREATE TABLE part_order(	"
					+ "order_id bigint,	"
					+ "part_id bigint,	"
					+ "amount bigint NOT NULL);");
			stat.addBatch("ALTER TABLE car ADD COLUMN customer_id bigint;");
			stat.addBatch("ALTER TABLE car	ADD FOREIGN KEY(customer_id) REFERENCES customer(customer_id);");
			stat.addBatch("ALTER TABLE order_in_work ADD COLUMN car_id bigint;");
			stat.addBatch("ALTER TABLE order_in_work ADD FOREIGN KEY(car_id) REFERENCES car(car_id);");
			stat.addBatch("ALTER TABLE order_in_work ADD COLUMN order_status_id tinyint;");
			stat.addBatch("ALTER TABLE order_in_work ADD FOREIGN KEY(order_status_id) REFERENCES order_status(order_status_id);");
			stat.addBatch("ALTER TABLE order_worker ADD FOREIGN KEY(worker_id) REFERENCES worker(worker_id);");
			stat.addBatch("ALTER TABLE order_worker ADD FOREIGN KEY(order_id) REFERENCES order_in_work(order_id);");
			stat.addBatch("ALTER TABLE worker ADD COLUMN spec_id bigint;");
			stat.addBatch("ALTER TABLE worker ADD FOREIGN KEY(spec_id) REFERENCES worker_specification(spec_id);");
			stat.addBatch("ALTER TABLE worker ADD COLUMN status_id tinyint;");
			stat.addBatch("ALTER TABLE worker ADD FOREIGN KEY (status_id) REFERENCES status(status_id);");
			stat.addBatch("ALTER TABLE part_order ADD FOREIGN KEY(order_id) REFERENCES order_in_work(order_id);");
			stat.addBatch("ALTER TABLE part_order ADD FOREIGN KEY(part_id) REFERENCES part(part_id);");
			stat.addBatch("ALTER TABLE order_worker ADD CONSTRAINT UNIQUE_worker_in_order UNIQUE(order_id, worker_id);");
			stat.addBatch("ALTER TABLE part_order ADD CONSTRAINT unigue_part_in_order UNIQUE(order_id, part_id);");
			stat.executeBatch();
			stat.close();
		} catch (SQLException ex) {
			LOG.debug(ex, ex);
		}
		finally
		{
			if (conn != null)
			{
				conn.close();
			}
			if (stat != null)
			{
				stat.close();
			}
		}

	}
}
