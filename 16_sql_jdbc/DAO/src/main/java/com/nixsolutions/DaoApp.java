package com.nixsolutions;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Car;
import entities.Customer;
import entities.OrderInWork;
import entities.OrderStatus;
import entities.OrderWorker;
import entities.Part;
import entities.PartOrder;
import entities.Status;
import entities.Worker;
import entities.WorkerSpecification;
import h2.CarDAOImpl;
import h2.CarDAOManualTransactionImpl;
import h2.CustomerDAOImpl;
import h2.OrderInWorkDAOImpl;
import h2.OrderStatusDAOImpl;
import h2.OrderWorkerDAOImpl;
import h2.PartDAOImpl;
import h2.PartOrderDAOImpl;
import h2.ServiceStationDAOFactoryImpl;
import h2.StatusDAOImpl;
import h2.WorkerDAOImpl;
import h2.WorkerSpecificationDAOImpl;

public class DaoApp {

	private final static Logger LOG = LogManager.getLogger(DaoApp.class);
	private static ServiceStationDAOFactoryImpl factory;
	private static ConnectionManager connMgr;

	public static void main(String[] args) throws Exception {

		try {
			LOG.trace("Creation factory...");
			factory = new ServiceStationDAOFactoryImpl();
			connMgr = new ConnectionManager();
		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		try {

			// Customer DAO
			LOG.trace("Creation DAO for customer...");
			CustomerDAOImpl customerDAO = (CustomerDAOImpl) factory.getDao(
					connMgr.getConnection(), Customer.class);
			Customer customer1 = new Customer();
			customer1.setf_name("Vincent");
			customer1.setl_name("Fin");
			customer1.setphone("+375454648484");
			LOG.trace("Create new customer....");
			customerDAO.create(customer1);
			// find by id
			LOG.trace("Find a customer by ID");
			Customer customer2 = customerDAO.findByPK(1);
			// update
			LOG.trace("Update found customer and update it in db");
			customer2.setf_name("John");
			customer2.setl_name("Smith");
			customerDAO.update(customer2);
			LOG.trace("Find a customer by name....");
			// /find by name
			Customer customer3 = customerDAO.findByName("Pupkin4");
			// delete
			LOG.trace("Delete found customer...");
			customerDAO.delete(customer3);

		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		// Car DAO
		try {
			LOG.trace("Creation DAO for car...");
			CarDAOImpl carDAO = (CarDAOImpl) factory.getDao(
					connMgr.getConnection(), Car.class);
			Car car1 = new Car();
			car1.setCustomer_id(1);
			car1.setDescription("Bla-bla-car");
			car1.setModel("BMW X5");
			car1.setVin("UINFD7346246242738942");
			LOG.trace("Add new customer to db");
			carDAO.create(car1);

			// /find by PK
			LOG.trace("find a car by ID");
			Car car2 = carDAO.findByPK(1);
			car2.setCustomer_id(2);
			car2.setVin("UINFD7346246242738943434");
			car2.setDescription("bla-bla-car2");
			LOG.trace("Update a found car in database...");
			carDAO.update(car2);

			// find by name
			LOG.trace("Find a car by name...");
			Car car3 = carDAO.findByName("VOLVO");
			LOG.trace("Delete a found car...");
			carDAO.delete(car3);
		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		// Car DAO Manual Transitions
		try {
			LOG.trace("Creation DAO for car with manual transaction...");
			CarDAOManualTransactionImpl carDAOWithManual = new CarDAOManualTransactionImpl(
					connMgr.getConnection());
			Car car1 = new Car();
			car1.setCustomer_id(1);
			car1.setDescription("Bla-bla-car");
			car1.setModel("BMW X5");
			car1.setVin("UINFD7346246242738942");
			LOG.trace("Add new customer to db");
			carDAOWithManual.create(car1);

			// /find by PK
			LOG.trace("find a car by ID");
			Car car2 = carDAOWithManual.findByPK(1);
			car2.setCustomer_id(2);
			car2.setVin("UINFD7346246242738943434");
			car2.setDescription("bla-bla-car2");
			LOG.trace("Update a found car in database...");
			carDAOWithManual.update(car2);

			// find by name
			LOG.trace("Find a car by name...");
			Car car3 = carDAOWithManual.findByName("VOLVO");
			LOG.trace("Delete a found car...");
			carDAOWithManual.delete(car3);
		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		// Order in work DAO
		try {
			OrderInWorkDAOImpl oiwDAO = (OrderInWorkDAOImpl) factory.getDao(
					connMgr.getConnection(), OrderInWork.class);

			OrderInWork oiw1 = new OrderInWork();
			oiw1.setCar_id(1);
			oiw1.setOrder_status_id(2);
			oiw1.setDatetime_start(new Timestamp(new Date().getTime()));
			oiw1.setDescription("bla-bla-order");
			oiwDAO.create(oiw1);

			// find by PK
			OrderInWork oiw2 = oiwDAO.findByPK(1);
			// update
			oiw2.setDatetime_end(new Timestamp(new Date().getTime()));
			oiw2.setOrder_status_id(3);
			oiwDAO.update(oiw2);

			// find by name
			OrderInWork oiw3 = oiwDAO.findByName("bla");
			// /try to delete null
			oiwDAO.delete(oiw2);
			// /

		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		// /Order status DAO
		try {
			LOG.trace("Create DAO for OrderStatus");
			OrderStatusDAOImpl orderStatusDAO = (OrderStatusDAOImpl) factory
					.getDao(connMgr.getConnection(), OrderStatus.class);

			OrderStatus os1 = new OrderStatus();
			os1.setOrder_status_name("new status");
			LOG.trace("Create a new order status...");
			orderStatusDAO.create(os1);

			// /find by PK
			LOG.trace("Find a order status by id");
			OrderStatus os2 = orderStatusDAO.findByPK(1);
			// update
			os2.setOrder_status_name(os2.getOrder_status_name() + " v2");
			LOG.trace("Update a order status in db...");
			orderStatusDAO.update(os2);

			// find by name
			LOG.trace("Find order status by name...");
			OrderStatus os3 = orderStatusDAO.findByName("refused");
			// try to delete will throw an exception
			LOG.trace("Trying to delete the order status");
			orderStatusDAO.delete(os3);
		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		try {
			// Order worker DAO
			LOG.trace("Creation DAO for order worker...");
			OrderWorkerDAOImpl owDAO = (OrderWorkerDAOImpl) factory.getDao(
					connMgr.getConnection(), OrderWorker.class);
			// create
			OrderWorker ow1 = new OrderWorker();
			ow1.setCompleted(false);
			ow1.setWorker_id(5);
			ow1.setId(5);
			LOG.trace("Create new order owrker in db");
			owDAO.create(ow1);

			// /find by id
			LOG.trace("Find an order worker by id... Result will be null");
			OrderWorker ow2 = owDAO.findByPK(1);
			// /find by name
			LOG.trace("Find an order worker by name... Result will be null");
			OrderWorker ow3 = owDAO.findByName("bla");

		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		try {
			LOG.trace("Creation DAO for part...");
			// /Part DAO
			PartDAOImpl partDAO = (PartDAOImpl) factory.getDao(
					connMgr.getConnection(), Part.class);
			Part p1 = new Part();
			p1.setPart_name("gear1");
			p1.setVendor("china boys");
			p1.setAmount(4);
			LOG.trace("Create a new part in db");
			partDAO.create(p1);

			LOG.trace("Find a aprt by ID");
			// find by id
			Part p2 = partDAO.findByPK(1);
			// update
			p2.setAmount(500);
			LOG.trace("Update a found part in db");
			partDAO.update(p2);

			// find by name
			LOG.trace("Found a part by name");
			Part p3 = partDAO.findByName("gear1");
			// /try to delete
			LOG.trace("Trying to delete a found part");
			partDAO.delete(p3);
		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		try {
			LOG.trace("Creation DAO for PartOrder");
			// PartOrder DAO
			PartOrderDAOImpl poDAO = (PartOrderDAOImpl) factory.getDao(
					connMgr.getConnection(), PartOrder.class);

			PartOrder po1 = new PartOrder();
			po1.setId(3);
			po1.setAmount(4);
			po1.setPartid(6);
			LOG.trace("Creation a new part order in db");
			poDAO.create(po1);

			// find by id will return null
			LOG.trace("Searching by ID and Name will return null always...");
			PartOrder po2 = poDAO.findByPK(3);
			PartOrder po3 = poDAO.findByName("bla");

		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		try {
			// Status DAO
			LOG.trace("Creation DAO for status...");
			StatusDAOImpl sDAO = (StatusDAOImpl) factory.getDao(
					connMgr.getConnection(), Status.class);

			Status s1 = new Status();
			s1.setStatus_name("very new status");
			LOG.trace("Creation new status in db");
			sDAO.create(s1);

			LOG.trace("Searching foe new status by name...");
			Status s2 = sDAO.findByName("very new status");
			// update
			s2.setStatus_name("status1");
			LOG.trace("Update a status in db...");
			sDAO.update(s2);

			// delete
			LOG.trace("Update a found status in db...");
			sDAO.delete(s2);
		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}
		try {
			LOG.trace("Creation DAO for worker...");
			// /Worker DAO
			WorkerDAOImpl workerDAO = (WorkerDAOImpl) factory.getDao(
					connMgr.getConnection(), Worker.class);

			Worker w1 = new Worker();
			w1.setF_name("MAx");
			w1.setL_name("Berghoff");
			w1.setStatus_id(1);
			w1.setSpec_id(1);
			LOG.trace("Create a new worker in db");
			workerDAO.create(w1);
			// get worker by name
			LOG.trace("Find a new worker by name");
			Worker w2 = workerDAO.findByName("Berghoff");
			// update
			w2.setSpec_id(2);
			w2.setStatus_id(2);
			LOG.trace("Update a worker in db");
			workerDAO.update(w2);
			// delete
			LOG.trace("Delete a worker from db");
			workerDAO.delete(w2);
		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}

		try {
			// Worker specification DAO
			LOG.trace("Creation DAO for worker...");
			WorkerSpecificationDAOImpl wsDAO = (WorkerSpecificationDAOImpl) factory
					.getDao(connMgr.getConnection(), WorkerSpecification.class);
			WorkerSpecification ws1 = new WorkerSpecification();
			ws1.setSpec_name("Cleaner");
			// create
			LOG.trace("Create a new specification in db");
			wsDAO.create(ws1);
			// find
			WorkerSpecification ws2 = wsDAO.findByName("Cleaner");
			// update
			ws2.setSpec_name("Ultra cleaner");
			LOG.trace("Update a found spec in db");
			wsDAO.update(ws2);
			// delete
			LOG.trace("Delete a found spec in db");
			wsDAO.delete(ws2);
		} catch (Exception ex) {
			LOG.debug(ex, ex);
		}
	}
}
