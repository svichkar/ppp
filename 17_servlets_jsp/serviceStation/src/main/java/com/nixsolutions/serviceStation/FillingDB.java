/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;

/**
 * @author mixeyes
 *
 */
public class FillingDB {
	private final static Logger logger = LogManager.getLogger();

	public FillingDB() {
		try {
			fillingDB();
		} catch (ClassNotFoundException | SQLException e) {

		}
	}

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		fillingDB();
	}

	public static void fillingDB() throws SQLException, ClassNotFoundException {
		Properties properties = new DbConnector().getProperties();

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(properties.getProperty("h2URL"), properties.getProperty("h2Login"),
					properties.getProperty("h2Password"));
			Statement stmt = conn.createStatement();
			/* sqllab.customer */

			stmt.addBatch("INSERT INTO sqllab.user_role (user_role_name) VALUES('manager');");
			stmt.addBatch("INSERT INTO sqllab.user_role (user_role_name) VALUES('customer');");
			stmt.addBatch("INSERT INTO sqllab.user_role (user_role_name) VALUES('worker');");
			stmt.addBatch("INSERT INTO sqllab.user_role (user_role_name) VALUES('storekeeper');");

			stmt.addBatch("INSERT INTO sqllab.user (user_login, user_password, user_role_id) "
					+ "VALUES('manager','manager','1');");
			stmt.addBatch("INSERT INTO sqllab.user (user_login, user_password, user_role_id) "
					+ "VALUES('customer1','customer','2');");
			stmt.addBatch("INSERT INTO sqllab.user (user_login, user_password, user_role_id) "
					+ "VALUES('customer2','customer','2');");
			stmt.addBatch("INSERT INTO sqllab.user (user_login, user_password, user_role_id) "
					+ "VALUES('customer3','customer','2');");
			stmt.addBatch("INSERT INTO sqllab.user (user_login, user_password, user_role_id) "
					+ "VALUES('customer4','customer','2');");
			stmt.addBatch("INSERT INTO sqllab.user (user_login, user_password, user_role_id) "
					+ "VALUES('worker','worker','3');");
			stmt.addBatch("INSERT INTO sqllab.user (user_login, user_password, user_role_id) "
					+ "VALUES('storekeeper','storekeeper','4');");

			stmt.addBatch("INSERT INTO sqllab.customer (first_name, last_name, phone ,user_id) "
					+ "VALUES('IVAN','IVANOV','1-23-3455-5435',2);");
			stmt.addBatch("INSERT INTO sqllab.customer (first_name, last_name, phone,user_id) "
					+ "VALUES('Petr','Petrov','23-3455-5435',3);");
			stmt.addBatch("INSERT INTO sqllab.customer (first_name, last_name, phone,user_id)"
					+ "VALUES('Fedor','Fedorov','1-23-3454735863',4);");
			stmt.addBatch("INSERT INTO sqllab.customer (first_name, last_name, phone,user_id) "
					+ "VALUES('Alex','Alkov','827382755-5435',5);");
			/* sqllab.car */
			stmt.addBatch("INSERT INTO sqllab.car (car_model, vin_number, car_description, customer_id ) "
					+ "VALUES('AUDI','1234567890qwertyu', 'forever first auto for tests',1);");
			stmt.addBatch("INSERT INTO sqllab.car (car_model, vin_number, customer_id ) "
					+ "VALUES('BMW','67123rtyu45890qwe',2);");
			stmt.addBatch("INSERT INTO sqllab.car (car_model, vin_number, car_description, customer_id ) "
					+ "VALUES('BMW','6712345890qwertyu', 'Some description',2);");
			stmt.addBatch("INSERT INTO sqllab.car (car_model, vin_number, customer_id ) "
					+ "VALUES('OPEL','5890qw671234ertyu',2);");
			stmt.addBatch("INSERT INTO sqllab.car (car_model, vin_number, customer_id ) "
					+ "VALUES('ВАЗ-2102','5w671890q234ertyu',3);");
			stmt.addBatch("INSERT INTO sqllab.car (car_model, vin_number, customer_id ) "
					+ "VALUES('DODGE CHARGER','5w67184ert90q23yu',4);");
			/* sqllab.worker_specialization */
			stmt.addBatch(
					"INSERT INTO sqllab.worker_specialization (specialization_name) " + "VALUES('mechanik low');");
			stmt.addBatch(
					"INSERT INTO sqllab.worker_specialization (specialization_name) " + "VALUES('mechanik high');");
			stmt.addBatch("INSERT INTO sqllab.worker_specialization (specialization_name) " + "VALUES('electric');");
			stmt.addBatch(
					"INSERT INTO sqllab.worker_specialization (specialization_name) " + "VALUES('diagnostician');");
			stmt.addBatch("INSERT INTO sqllab.worker_specialization (specialization_name) " + "VALUES('engine');");
			/* sqllab.worker_status */
			stmt.addBatch("INSERT INTO sqllab.worker_status (worker_status_name) " + "VALUES('busy');");
			stmt.addBatch("INSERT INTO sqllab.worker_status (worker_status_name) " + "VALUES('free');");
			stmt.addBatch("INSERT INTO sqllab.worker_status (worker_status_name) " + "VALUES('ill');");
			stmt.addBatch("INSERT INTO sqllab.worker_status (worker_status_name) " + "VALUES('vacation');");
			/* sqllab.worker */
			stmt.addBatch("INSERT INTO sqllab.worker (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(1, 'Ivan', 'Ivanov',1);");
			stmt.addBatch("INSERT INTO sqllab.worker (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(1, 'Petr', 'Ivanov',2);");
			stmt.addBatch("INSERT INTO sqllab.worker  (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(2, 'Fedya', 'Ivanov',3);");
			stmt.addBatch("INSERT INTO sqllab.worker  (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(3, 'Anton', 'Ivanov',2);");
			stmt.addBatch("INSERT INTO sqllab.worker  (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(4, 'Aleksey', 'Ivanov',4);");
			stmt.addBatch("INSERT INTO sqllab.worker  (specialization_id, first_name, last_name, worker_status_id) "
					+ "VALUES(5, 'Evgeniy', 'Ivanov',1);");
			/* sqllab.part */
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('tyaga ВАЗ', '81-56fa354fa', '6');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('brake pads OPEL', '5634-fafa815', '12');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount)"
					+ "VALUES('brake pads BMW', '54-fafa81563', '12');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('tyaga BMW', '8156fa354-fa', '6');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('shrovaya BMW', '8156354-fafa', '5');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('brake pads AUDI', '54-fafa81563', '12');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('tyaga AUDI', '8156fa354-fa', '6');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('lamp H4 PHILIPS', '6354-f815afa', '10');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('lamp H4 OSRAM', '81a56354-faf', '10');");
			stmt.addBatch("INSERT INTO sqllab.part (part_name, vendor, amount) "
					+ "VALUES('shrovaya AUDI', '8156354-fafa', '5');");
			/* sqllab.order_status */
			stmt.addBatch("INSERT INTO sqllab.order_status (order_status_name )VALUES('waiting');");
			stmt.addBatch("INSERT INTO sqllab.order_status (order_status_name )VALUES('in work');");
			stmt.addBatch("INSERT INTO sqllab.order_status (order_status_name )VALUES('ready');");
			/* sqllab.order_in_work */
			stmt.addBatch(
					"INSERT INTO sqllab.order_in_work (order_description,datetime_start, datetime_finish, order_status_id ,car_ID  ) "
							+ "VALUES('change lamp',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),'1','1');");
			stmt.addBatch(
					"INSERT INTO sqllab.order_in_work (order_description,datetime_start ,order_status_id ,car_ID  ) "
							+ "VALUES('change brake pads',CURRENT_TIMESTAMP(),'2','2');");
			stmt.addBatch(
					"INSERT INTO sqllab.order_in_work (order_description,datetime_start,datetime_finish  ,order_status_id ,car_ID  ) "
							+ "VALUES('change brake pads','2015-11-05 09:49:49.71',CURRENT_TIMESTAMP(),'3','5');");
			stmt.addBatch(
					"INSERT INTO sqllab.order_in_work (order_description,datetime_start,datetime_finish  ,order_status_id ,car_ID  ) "
							+ "VALUES('change tyaga','2015-11-05 09:49:49.71',CURRENT_TIMESTAMP(),'3','4');");

			stmt.addBatch("INSERT INTO sqllab.part_order (order_id ,part_id,amount  ) VALUES('1','3','2');");
			stmt.addBatch("INSERT INTO sqllab.part_order (order_id ,part_id,amount  ) VALUES('2','8','4');");
			stmt.addBatch("INSERT INTO sqllab.part_order (order_id ,part_id,amount  ) VALUES('2','3','2');");
			stmt.addBatch("INSERT INTO sqllab.part_order (order_id ,part_id,amount  ) VALUES('4','10','1');");

			stmt.executeBatch();
			stmt.close();
			logger.trace("db was created");
		} catch (SQLException ex) {
			logger.error("SQL Error code: " + ex.getErrorCode() + ". Details: " + ex.getMessage());
		} finally {
			conn.close();
		}

	}
}
