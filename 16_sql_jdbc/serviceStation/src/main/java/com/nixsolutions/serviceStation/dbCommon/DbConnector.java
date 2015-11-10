/**
 * 
 */
package com.nixsolutions.serviceStation.dbCommon;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author mixeyes
 *
 */
public class DbConnector {
	private final static Logger logger = LogManager.getLogger(DbConnector.class);
	private Connection conn;
	private Properties properties;

	public DbConnector() throws SQLException, ClassNotFoundException {
		this.properties = getProperties();
		Class.forName("org.h2.Driver");

		conn = DriverManager.getConnection(properties.getProperty("h2URL"), properties.getProperty("h2Login"),
				properties.getProperty("h2Password"));
	}

	public Connection getConnection() {
		return conn;
	}

	private Properties getProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			logger.debug("Try to read \"h2db.properties\" file");
			input = new FileInputStream("h2db.properties");

			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			logger.error(ex.getMessage(), new IOException(ex));
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), new IOException(e));
				}
			}
		}
		return prop;
	}

	public void closeConnection() throws SQLException {
		// add application code here
		logger.trace("close connection to db");
		conn.close();

	}

	/** create All Tables */
	public void createAllTables() {
		try {
			Statement stmt = conn.createStatement();
			// stmt.addBatch("SET SCHEMA sqllab;");
			stmt.addBatch("CREATE TABLE worker_specialization( " + "specialization_id INT IDENTITY,"
					+ "specialization_name VARCHAR(256) NOT NULL);");
			stmt.addBatch("CREATE TABLE worker_status( " + "worker_status_id INT IDENTITY, "
					+ "worker_status_name VARCHAR(128) NOT NULL); ");
			stmt.addBatch("CREATE TABLE worker( " + "worker_id INT IDENTITY, " + "specialization_id INT NOT NULL,"
					+ "FOREIGN KEY (specialization_id) REFERENCES  worker_specialization(specialization_id),"
					+ "first_name VARCHAR(128) NOT NULL," + "last_name VARCHAR(128) NOT NULL, "
					+ "worker_status_id INT NOT NULL,FOREIGN KEY (worker_status_id) REFERENCES  worker_status(worker_status_id),);");
			stmt.addBatch("CREATE TABLE order_in_work( " + "order_id INT IDENTITY, "
					+ "order_description VARCHAR(512) NOT NULL, " + "datetime_start TIMESTAMP NOT NULL, "
					+ "datetime_finish TIMESTAMP);");
			stmt.addBatch("CREATE TABLE order_status( " + "order_status_id INT IDENTITY, "
					+ "order_status_name VARCHAR(128) NOT NULL);");
			stmt.addBatch("CREATE TABLE part( " + "part_id INT IDENTITY, " + "part_name VARCHAR(128) NOT NULL, "
					+ "vendor VARCHAR(128) NOT NULL, " + "amount TINYINT);");
			stmt.addBatch("CREATE TABLE part_order( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "part_id INT NOT NULL,"
					+ "FOREIGN KEY (part_id) REFERENCES  part(part_id), amount TINYINT);");
			// create car table
			stmt.addBatch("CREATE TABLE car( " + "car_id INT IDENTITY, " + "car_model VARCHAR(128) NOT NULL, "
					+ "vin_number VARCHAR(17) NOT NULL UNIQUE, " + "car_description VARCHAR(256));");
			stmt.addBatch(
					"CREATE TABLE customer( " + "customer_id INT IDENTITY, " + "first_name VARCHAR(128) NOT NULL, "
							+ "last_name VARCHAR(128) NOT NULL, " + "phone VARCHAR(32));");
			stmt.addBatch("CREATE TABLE order_worker( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "worker_id INT IDENTITY, "
					+ "FOREIGN KEY (worker_id) REFERENCES  worker(worker_id), "
					+ "isCompleted BOOLEAN NOT NULL DEFAULT false);");

			stmt.addBatch("ALTER TABLE order_in_work " + "ADD COLUMN order_status_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE order_in_work "
					+ "ADD FOREIGN KEY(order_status_id ) REFERENCES order_status (order_status_id );");
			stmt.addBatch("ALTER TABLE order_in_work " + "ADD COLUMN car_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE order_in_work " + "ADD FOREIGN KEY(car_id ) REFERENCES car (car_id );");
			stmt.addBatch("ALTER TABLE car " + "ADD COLUMN customer_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE car " + "ADD FOREIGN KEY(customer_id ) REFERENCES car (customer_id );");

			stmt.addBatch("CREATE INDEX order_idx ON order_in_work(order_id);");
			stmt.addBatch("CREATE INDEX vendorx ON part(vendor);");
			stmt.addBatch("CREATE INDEX vin_numberx ON car(vin_number);");
			stmt.addBatch("CREATE INDEX last_namex ON customer(last_name);");
			stmt.addBatch("CREATE INDEX phonex ON customer(phone);");
			stmt.executeBatch();
			stmt.close();
			logger.trace("db was created");
		} catch (SQLException ex) {
			logger.error("SQL Error code: " + ex.getErrorCode() + ". Details: " + ex.getMessage());
		}

	}

	public void deleteAllTablesWithAllData() {
		try {
			logger.trace(
					"Send query \"DROP TABLE car ,customer ,order_in_work ,part ,part_order ,status ,worker ,worker_specialization ,worker_status ,order_status ,order_worker;\"");

			PreparedStatement stmt = conn.prepareStatement(
					"DROP TABLE worker_specialization, worker_status, worker, order_in_work, order_status, part, part_order, car, customer, order_worker;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("All tables was deleted");
			else
				logger.debug("All tables was not deleted");
			closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void baseFillingAllTables() {
		try {
			Statement stmt = conn.createStatement();
			/* customer */
			stmt.addBatch(
					"INSERT INTO customer (first_name, last_name, phone) "
					+ "VALUES('IVAN','IVANOV','1-23-3455-5435');");
			stmt.addBatch(
					"INSERT INTO customer (first_name, last_name, phone) "
					+ "VALUES('Petr','Petrov','23-3455-5435');");
			stmt.addBatch(
					"INSERT INTO customer (first_name, last_name, phone)"
					+ "VALUES('Fedor','Fedorov','1-23-3454735863');");
			stmt.addBatch(
					"INSERT INTO customer (first_name, last_name, phone) "
					+ "VALUES('Alex','Alkov','827382755-5435');");
			/* car */
			stmt.addBatch(
					"INSERT INTO car (car_model, vin_number, customer_id ) "
					+ "VALUES('AUDI','1234567890qwertyu',1);");
			stmt.addBatch(
					"INSERT INTO car (car_model, vin_number, customer_id ) "
					+ "VALUES('BMW','6712345890qwertyu',2);");
			stmt.addBatch(
					"INSERT INTO car (car_model, vin_number, customer_id ) "
					+ "VALUES('OPEL','5890qw671234ertyu',2);");
			stmt.addBatch(
					"INSERT INTO car (car_model, vin_number, customer_id ) "
					+ "VALUES('ВАЗ-2102','5w671890q234ertyu',3);");
			stmt.addBatch(
					"INSERT INTO car (car_model, vin_number, customer_id ) "
					+ "VALUES('DODGE CHARGER','5w67184ert90q23yu',4);");
			/* worker_specialization */
			stmt.addBatch("INSERT INTO worker_specialization (specialization_name) "
					+ "VALUES('mechanik low');");
			stmt.addBatch("INSERT INTO worker_specialization (specialization_name) "
					+ "VALUES('mechanik high');");
			stmt.addBatch("INSERT INTO worker_specialization (specialization_name) "
					+ "VALUES('electric');");
			stmt.addBatch("INSERT INTO worker_specialization (specialization_name) "
					+ "VALUES('diagnostician');");
			stmt.addBatch("INSERT INTO worker_specialization (specialization_name) "
					+ "VALUES('engine');");
			/* worker_status */
			stmt.addBatch("INSERT INTO worker_status (worker_status_name) "
					+ "VALUES('busy');");
			stmt.addBatch("INSERT INTO worker_status (worker_status_name) "
					+ "VALUES('free');");
			stmt.addBatch("INSERT INTO worker_status (worker_status_name) "
					+ "VALUES('ill');");
			stmt.addBatch("INSERT INTO worker_status (worker_status_name) "
					+ "VALUES('vacation');");
			/* worker */
			stmt.addBatch(
					"INSERT INTO worker(specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(1, 'Ivan', 'Ivanov',1);");
			stmt.addBatch(
					"INSERT INTO worker(specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(1, 'Petr', 'Ivanov',2);");
			stmt.addBatch(
					"INSERT INTO worker (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(2, 'Fedya', 'Ivanov',3);");
			stmt.addBatch(
					"INSERT INTO worker (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(3, 'Anton', 'Ivanov',2);");
			stmt.addBatch(
					"INSERT INTO worker (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(4, 'Aleksey', 'Ivanov',4);");
			stmt.addBatch(
					"INSERT INTO worker (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(5, 'Evgeniy', 'Ivanov',1);");
			/* part */
			stmt.addBatch("INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('tyaga ВАЗ', '81-56fa354fa', '6');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('brake pads OPEL', '5634-fafa815', '12');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount)"
					+ "VALUES('brake pads BMW', '54-fafa81563', '12');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('tyaga BMW', '8156fa354-fa', '6');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('shrovaya BMW', '8156354-fafa', '5');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('brake pads AUDI', '54-fafa81563', '12');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('tyaga AUDI', '8156fa354-fa', '6');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('lamp H4 PHILIPS', '6354-f815afa', '10');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('lamp H4 OSRAM', '81a56354-faf', '10');");
			stmt.addBatch(
					"INSERT INTO part (part_name, vendor, amount) "
					+ "VALUES('shrovaya AUDI', '8156354-fafa', '5');");
			/* order_status */
			stmt.addBatch("INSERT INTO order_status (order_status_name )VALUES('waiting');");
			stmt.addBatch("INSERT INTO order_status (order_status_name )VALUES('in work');");
			stmt.addBatch("INSERT INTO order_status (order_status_name )VALUES('ready');");
			/* order_in_work */
			stmt.addBatch(
					"INSERT INTO order_in_work (order_description,datetime_start ,order_status_id ,car_ID  ) "
					+ "VALUES('change lamp',CURRENT_TIMESTAMP(),'1','1');");
			stmt.addBatch(
					"INSERT INTO order_in_work (order_description,datetime_start ,order_status_id ,car_ID  ) "
					+ "VALUES('change brake pads',CURRENT_TIMESTAMP(),'2','2');");
			stmt.addBatch(
					"INSERT INTO order_in_work (order_description,datetime_start,datetime_finish  ,order_status_id ,car_ID  ) "
					+ "VALUES('change brake pads','2015-11-05 09:49:49.71',CURRENT_TIMESTAMP(),'3','3');");
			stmt.addBatch(
					"INSERT INTO order_in_work (order_description,datetime_start,datetime_finish  ,order_status_id ,car_ID  ) "
					+ "VALUES('change tyaga','2015-11-05 09:49:49.71',CURRENT_TIMESTAMP(),'3','4');");

			stmt.addBatch("INSERT INTO part_order (order_id ,part_id,amount  ) VALUES(1,3,2);");
			stmt.addBatch("INSERT INTO part_order (order_id ,part_id,amount  ) VALUES('2','8','4');");
			stmt.addBatch("INSERT INTO part_order (order_id ,part_id,amount  ) VALUES('3','9','4');");
			stmt.addBatch("INSERT INTO part_order (order_id ,part_id,amount  ) VALUES('4','10','1');");

			stmt.executeBatch();
			stmt.close();
			logger.trace("db was created");
		} catch (SQLException ex) {
			logger.error("SQL Error code: " + ex.getErrorCode() + ". Details: " + ex.getMessage());
		}

	}

}
