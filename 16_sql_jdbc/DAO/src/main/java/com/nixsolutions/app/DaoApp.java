package com.nixsolutions.app;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.impl.CarDAOImpl;
import com.nixsolutions.dao.impl.CarDAOManualTransactionImpl;
import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.dao.impl.OrderInWorkDAOImpl;
import com.nixsolutions.dao.impl.OrderStatusDAOImpl;
import com.nixsolutions.dao.impl.OrderWorkerDAOImpl;
import com.nixsolutions.dao.impl.PartDAOImpl;
import com.nixsolutions.dao.impl.PartOrderDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.StatusDAOImpl;
import com.nixsolutions.dao.impl.WorkerDAOImpl;
import com.nixsolutions.dao.impl.WorkerSpecificationDAOImpl;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderStatus;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.entities.Part;
import com.nixsolutions.entities.PartOrder;
import com.nixsolutions.entities.Status;
import com.nixsolutions.entities.Worker;
import com.nixsolutions.entities.WorkerSpecification;

public class DaoApp {

	private final static Logger LOG = LogManager.getLogger(DaoApp.class);
	private static ServiceStationDAOFactoryImpl factory;

	public static void main(String[] args) throws Exception {

		try {
			LOG.trace("Creation factory...");
			factory = new ServiceStationDAOFactoryImpl();
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		try {

			// Customer DAO
			LOG.trace("Creation DAO for customer...");
			CustomerDAOImpl customerDAO = (CustomerDAOImpl) factory
					.getDao(Customer.class);
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
			// / delete
			Customer customerToDelete = customerDAO.getAll().get(
					customerDAO.getAll().size() - 1);
			customerDAO.delete(customerToDelete);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		// Car DAO
		try {
			LOG.trace("Creation DAO for car...");
			CarDAOImpl carDAO = (CarDAOImpl) factory.getDao(Car.class);
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

			LOG.trace("Delete a found car...");
			Car carToDelete = carDAO.getAll().get(carDAO.getAll().size() - 1);
			carDAO.delete(carToDelete);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		// Car DAO Manual Transitions
		try {
			LOG.trace("Creation DAO for car with manual transaction...");
			CarDAOManualTransactionImpl carDAOWithManual = new CarDAOManualTransactionImpl();
			// /find by PK
			LOG.trace("find a car by ID");
			Car car2 = carDAOWithManual.findByPK(1);
			car2.setCustomer_id(2);
			car2.setVin("UINFD7346246242738943434");
			car2.setDescription("bla-bla-car2");
			LOG.trace("Update a found car in database...");
			carDAOWithManual.update(car2);

		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		// Order in work DAO
		try {
			OrderInWorkDAOImpl oiwDAO = (OrderInWorkDAOImpl) factory
					.getDao(OrderInWork.class);
			// find by PK
			LOG.trace("Find order worker record.... Should return null");
			OrderInWork oiw2 = oiwDAO.findByPK(1);
			// update
			LOG.trace("Update order worker record....");
			oiw2.setDatetime_end(new Timestamp(new Date().getTime()));
			oiw2.setOrder_status_id(3);
			oiwDAO.update(oiw2);

		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		// /Order status DAO
		try {
			LOG.trace("Create DAO for OrderStatus");
			OrderStatusDAOImpl orderStatusDAO = (OrderStatusDAOImpl) factory
					.getDao(OrderStatus.class);

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

			// try to delete will throw an exception
			LOG.trace("Trying to delete the order status");
			OrderStatus os3 = orderStatusDAO.getAll().get(
					orderStatusDAO.getAll().size() - 1);
			orderStatusDAO.delete(os3);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		try {
			// Order worker DAO
			LOG.trace("Creation DAO for order worker...");
			OrderWorkerDAOImpl owDAO = (OrderWorkerDAOImpl) factory
					.getDao(OrderWorker.class);
			// /find by id
			LOG.trace("Find an order worker by id... Result will be null");
			OrderWorker ow2 = owDAO.findByPK(1);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		try {
			LOG.trace("Creation DAO for part...");
			// /Part DAO
			PartDAOImpl partDAO = (PartDAOImpl) factory.getDao(Part.class);
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

			// /try to delete
			LOG.trace("Trying to delete a found part");
			Part p3 = partDAO.getAll().get(partDAO.getAll().size() - 1);
			partDAO.delete(p3);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		try {
			LOG.trace("Creation DAO for PartOrder");
			// PartOrder DAO
			PartOrderDAOImpl poDAO = (PartOrderDAOImpl) factory
					.getDao(PartOrder.class);
			// find by id will return null
			LOG.trace("Searching by ID and Name will return null always...");
			PartOrder po2 = poDAO.findByPK(3);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		try {
			// Status DAO
			LOG.trace("Creation DAO for status...");
			StatusDAOImpl sDAO = (StatusDAOImpl) factory.getDao(Status.class);

			Status s1 = new Status();
			s1.setStatus_name("very new status");
			LOG.trace("Creation new status in db");
			sDAO.create(s1);
			// find
			Status s2 = sDAO.findByPK(1);
			// update
			s2.setStatus_name("status1");
			LOG.trace("Update a status in db...");
			sDAO.update(s2);
			// delete
			LOG.trace("Update a found status in db...");
			Status s3 = sDAO.getAll().get(sDAO.getAll().size() - 1);
			sDAO.delete(s3);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}
		try {
			LOG.trace("Creation DAO for worker...");
			// /Worker DAO
			WorkerDAOImpl workerDAO = (WorkerDAOImpl) factory
					.getDao(Worker.class);

			// find by id
			Worker w2 = workerDAO.findByPK(1);
			// update
			w2.setSpec_id(2);
			w2.setStatus_id(2);
			LOG.trace("Update a worker in db");
			workerDAO.update(w2);
			// delete
			LOG.trace("Delete a worker from db");
			Worker w3 = workerDAO.getAll().get(workerDAO.getAll().size() - 1);
			workerDAO.delete(w3);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

		try {
			// Worker specification DAO
			LOG.trace("Creation DAO for worker...");
			WorkerSpecificationDAOImpl wsDAO = (WorkerSpecificationDAOImpl) factory
					.getDao(WorkerSpecification.class);
			WorkerSpecification ws1 = new WorkerSpecification();
			ws1.setSpec_name("Cleaner");
			// create
			LOG.trace("Create a new specification in db");
			wsDAO.create(ws1);
			// update
			WorkerSpecification ws2 = wsDAO.findByPK(1);
			ws2.setSpec_name("Ultra cleaner");
			LOG.trace("Update a found spec in db");
			wsDAO.update(ws2);
			// delete
			LOG.trace("Delete a found spec in db");
			WorkerSpecification ws3 = wsDAO.getAll().get(
					wsDAO.getAll().size() - 1);
			wsDAO.delete(ws3);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}
	}
}
