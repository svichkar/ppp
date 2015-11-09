package com.nixsolutions.sql_jdbc;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Car;
import entities.Customer;
import entities.OrderInWork;
import entities.OrderPart;
import entities.OrderStatus;
import entities.OrderWorker;
import entities.Part;
import entities.PersistenceException;
import entities.Status;
import entities.Worker;
import entities.WorkerSpecialization;
import entities.WorkerStatus;
import h2.CarDAOImpl;
import h2.CustomerDAOImpl;
import h2.H2DAOFactoryImpl;
import h2.OrderInWorkDAOImpl;
import h2.OrderPartDAOImpl;
import h2.OrderStatusDAOImpl;
import h2.OrderWorkerDAOImpl;
import h2.PartDAOImpl;
import h2.StatusDAOImpl;
import h2.WorkerDAOImpl;
import h2.WorkerSpecializationDAOImpl;
import h2.WorkerStatusDAOImpl;

public class DAOApp {

	public static Logger LOG = LogManager.getLogger(DAOApp.class.getName());

	public static void main(String[] args) throws ClassNotFoundException {
		H2DAOFactoryImpl daoFactory = new H2DAOFactoryImpl();
		try {
			Connection conn = daoFactory.getContext();
			//Customer
			CustomerDAOImpl customerDAO = (CustomerDAOImpl) daoFactory.getDao(conn, Customer.class);
			List<Customer> customerList = customerDAO.getAll();
			Customer tCustomer = customerDAO.create();
			List<Customer> customerListPost = customerDAO.getAll();
			tCustomer.setFirstName("Test");
			tCustomer.setLastName("Testson");
			tCustomer.setPhone("Nokia");
			customerDAO.update(tCustomer);
			customerList = customerDAO.getAll();
			//Part
			PartDAOImpl partDAO = (PartDAOImpl) daoFactory.getDao(conn, Part.class);
			List<Part> partList = partDAO.getAll();
			Part tPart = partDAO.create();
			List<Part> partListPost = partDAO.getAll();
			tPart.setAmount(10);
			tPart.setPartName("Test Part");
			tPart.setVendor("Test Vendor");
			partDAO.update(tPart);
			partList = partDAO.getAll();
			//WorkerSpecialization
			WorkerSpecializationDAOImpl workerSpecDAO = (WorkerSpecializationDAOImpl) daoFactory.getDao(conn, WorkerSpecialization.class);
			List<WorkerSpecialization> workerSpecList = workerSpecDAO.getAll();
			WorkerSpecialization tSpec = workerSpecDAO.create();
			List<WorkerSpecialization> workerSpecListPost = workerSpecDAO.getAll();
			tSpec.setSpecName("Test Spec Name");
			workerSpecDAO.update(tSpec);
			workerSpecList = workerSpecDAO.getAll();
			//Status
			StatusDAOImpl statusDAO = (StatusDAOImpl) daoFactory.getDao(conn, Status.class);
			List<Status> statusList = statusDAO.getAll();
			Status tStatus = statusDAO.create();
			List<Status> statusListPost = statusDAO.getAll();
			tStatus.setStatusName("Test Status");
			statusDAO.update(tStatus);
			statusList = statusDAO.getAll();
			//OrderStatus
			OrderStatusDAOImpl orderStatusDAO = (OrderStatusDAOImpl) daoFactory.getDao(conn, OrderStatus.class);
			List<OrderStatus> orderStatusList = orderStatusDAO.getAll();
			OrderStatus tOrderStatus = orderStatusDAO.create();
			List<OrderStatus> orderStatusListPost = orderStatusDAO.getAll();
			tOrderStatus.setOrderStatusName("Test Status");
			orderStatusDAO.update(tOrderStatus);
			orderStatusList = orderStatusDAO.getAll();
			//Car
			CarDAOImpl carDAO = (CarDAOImpl) daoFactory.getDao(conn, Car.class);
			List<Car> carList = carDAO.getAll();
			Car tCar = carDAO.create();
			List<Car> carListPost = carDAO.getAll();
			tCar.setCustomerId(tCustomer.getId());
			tCar.setDescription("Test Description");
			tCar.setModel("Audi Unknown");
			tCar.setVin("12345678901112131");
			carDAO.update(tCar);
			carList = carDAO.getAll();
			//Worker
			WorkerDAOImpl workerDAO = (WorkerDAOImpl) daoFactory.getDao(conn, Worker.class);
			List<Worker> workerList = workerDAO.getAll();
			Worker tWorker = workerDAO.create();
			List<Worker> workerListPost = workerDAO.getAll();
			tWorker.setFirstName("Test First Name");
			tWorker.setLastName("Test Last Name");
			tWorker.setSpecializationId(tSpec.getId());
			workerDAO.update(tWorker);
			workerList = workerDAO.getAll();
			//WorkerStatus
			WorkerStatusDAOImpl workerStatusDAO = (WorkerStatusDAOImpl) daoFactory.getDao(conn, WorkerStatus.class);
			List<WorkerStatus> workerStatusList = workerStatusDAO.getAll();
			WorkerStatus tWorkerStatus = new WorkerStatus(tWorker.getId(), tStatus.getId());
			tWorkerStatus = workerStatusDAO.createFrom(tWorkerStatus);
			List<WorkerStatus> workerStatusListPost = workerStatusDAO.getAll();
			//Create second status to update relationship
			Status sStatus = new Status("Second Status");
			sStatus = statusDAO.createFrom(sStatus);
			//
			tWorkerStatus.setStatusId(sStatus.getId());
			workerStatusDAO.update(tWorkerStatus);
			workerStatusList = workerStatusDAO.getAll();
			//OrderInWork
			OrderInWorkDAOImpl orderDAO = (OrderInWorkDAOImpl) daoFactory.getDao(conn, OrderInWork.class);
			List<OrderInWork> orderList = orderDAO.getAll();
			OrderInWork tOrder = orderDAO.create();
			List<OrderInWork> orderListPost = orderDAO.getAll();
			tOrder.setCarId(tCar.getId());
			tOrder.setDescription("Test Description");
			tOrder.setOrderStatusId(tOrderStatus.getId());
			tOrder.setTimestampFinish(new Timestamp(new Date().getTime()));
			tOrder.setTimestampStart(new Timestamp(new Date().getTime()));
			orderDAO.update(tOrder);
			orderList = orderDAO.getAll();
			//OrderPart
			OrderPartDAOImpl orderPartDAO = (OrderPartDAOImpl) daoFactory.getDao(conn, OrderPart.class);
			List<OrderPart> orderPartList = orderPartDAO.getAll();
			OrderPart tOrderPart = new OrderPart(tOrder.getId(), tPart.getId(), 20);
			tOrderPart = orderPartDAO.createFrom(tOrderPart);
			List<OrderPart> orderPartListPost = orderPartDAO.getAll();
			tOrderPart.setUsedAmount(10);
			orderPartDAO.update(tOrderPart);
			orderPartList = orderPartDAO.getAll();
			//OrderWorker
			OrderWorkerDAOImpl orderWorkerDAO = (OrderWorkerDAOImpl) daoFactory.getDao(conn, OrderWorker.class);
			List<OrderWorker> orderWorkerList = orderWorkerDAO.getAll();
			OrderWorker tOrderWorker = new OrderWorker(tOrder.getId(), tWorker.getId(), "N");
			tOrderWorker = orderWorkerDAO.createFrom(tOrderWorker);
			List<OrderWorker> orderWorkerListPost = orderWorkerDAO.getAll();
			tOrderWorker.setIsCompleted("Y");
			orderWorkerDAO.update(tOrderWorker);
			orderWorkerList = orderWorkerDAO.getAll();

			//now delete all entities
			orderWorkerDAO.delete(tOrderWorker);
			orderWorkerList = orderWorkerDAO.getAll();
			orderPartDAO.delete(tOrderPart);
			orderPartList = orderPartDAO.getAll();
			orderDAO.delete(tOrder);
			orderList = orderDAO.getAll();
			workerStatusDAO.delete(tWorkerStatus);
			workerStatusList = workerStatusDAO.getAll();
			workerDAO.delete(tWorker);
			workerList = workerDAO.getAll();
			carDAO.delete(tCar);
			carList = carDAO.getAll();
			orderStatusDAO.delete(tOrderStatus);
			orderStatusList = orderStatusDAO.getAll();
			statusDAO.delete(sStatus);
			statusDAO.delete(tStatus);
			statusList = statusDAO.getAll();
			workerSpecDAO.delete(tSpec);
			workerSpecList = workerSpecDAO.getAll();
			partDAO.delete(tPart);
			partList = partDAO.getAll();
			customerDAO.delete(tCustomer);
			customerList = customerDAO.getAll();
		} catch (PersistenceException ex) {
			LOG.error(ex);
		}
	}

}
