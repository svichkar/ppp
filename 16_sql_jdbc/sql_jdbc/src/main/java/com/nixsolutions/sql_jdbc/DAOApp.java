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
import h2.CarDAO;
import h2.CustomerDAO;
import h2.H2DAOFactory;
import h2.OrderInWorkDAO;
import h2.OrderPartDAO;
import h2.OrderStatusDAO;
import h2.OrderWorkerDAO;
import h2.PartDAO;
import h2.StatusDAO;
import h2.WorkerDAO;
import h2.WorkerSpecializationDAO;
import h2.WorkerStatusDAO;

public class DAOApp {

	public static Logger LOG = LogManager.getLogger(DAOApp.class.getName());

	public static void main(String[] args) throws ClassNotFoundException {
		H2DAOFactory daoFactory = new H2DAOFactory();
		try {
			Connection conn = daoFactory.getContext();
			//Customer
			CustomerDAO customerDAO = (CustomerDAO) daoFactory.getDao(conn, Customer.class);
			List<Customer> customerList = customerDAO.getAll();
			Customer tCustomer = customerDAO.create();
			List<Customer> customerListPost = customerDAO.getAll();
			tCustomer.setFirstName("Test");
			tCustomer.setLastName("Testson");
			tCustomer.setPhone("Nokia");
			customerDAO.update(tCustomer);
			customerList = customerDAO.getAll();
			customerDAO.delete(tCustomer);
			customerList = customerDAO.getAll();
			//Car
			CarDAO carDAO = (CarDAO) daoFactory.getDao(conn, Car.class);
			List<Car> carList = carDAO.getAll();
			Car tCar = carDAO.create();
			List<Car> carListPost = carDAO.getAll();
			tCar.setCustomerId(1);
			tCar.setDescription("Test Description");
			tCar.setModel("Audi Unknown");
			tCar.setVin("12345678901112131");
			carDAO.update(tCar);
			carList = carDAO.getAll();
			carDAO.delete(tCar);
			carList = carDAO.getAll();
			//OrderInWork
			OrderInWorkDAO orderDAO = (OrderInWorkDAO) daoFactory.getDao(conn, OrderInWork.class);
			List<OrderInWork> orderList = orderDAO.getAll();
			OrderInWork tOrder = orderDAO.create();
			List<OrderInWork> orderListPost = orderDAO.getAll();
			tOrder.setCarId(1);
			tOrder.setDescription("Test Description");
			tOrder.setOrderStatusId(1);
			tOrder.setTimestampFinish(new Timestamp(new Date().getTime()));
			tOrder.setTimestampStart(new Timestamp(new Date().getTime()));
			orderDAO.update(tOrder);
			orderList = orderDAO.getAll();
			orderDAO.delete(tOrder);
			orderList = orderDAO.getAll();
			//OrderPart
			OrderPartDAO orderPartDAO = (OrderPartDAO) daoFactory.getDao(conn, OrderPart.class);
			List<OrderPart> orderPartList = orderPartDAO.getAll();
			OrderPart tOrderPart = new OrderPart(1, 1, 20);
			tOrderPart = orderPartDAO.createFrom(tOrderPart);
			List<OrderPart> orderPartListPost = orderPartDAO.getAll();
			tOrderPart.setUsedAmount(1);
			orderPartDAO.update(tOrderPart);
			orderPartList = orderPartDAO.getAll();
			orderPartDAO.delete(tOrderPart);
			orderPartList = orderPartDAO.getAll();
			//OrderStatus
			OrderStatusDAO orderStatusDAO = (OrderStatusDAO) daoFactory.getDao(conn, OrderStatus.class);
			List<OrderStatus> orderStatusList = orderStatusDAO.getAll();
			OrderStatus tOrderStatus = orderStatusDAO.create();
			List<OrderStatus> orderStatusListPost = orderStatusDAO.getAll();
			tOrderStatus.setOrderStatusName("Test Status");
			orderStatusDAO.update(tOrderStatus);
			orderStatusList = orderStatusDAO.getAll();
			orderStatusDAO.delete(tOrderStatus);
			orderStatusList = orderStatusDAO.getAll();
			//OrderWorker
			OrderWorkerDAO orderWorkerDAO = (OrderWorkerDAO) daoFactory.getDao(conn, OrderWorker.class);
			List<OrderWorker> orderWorkerList = orderWorkerDAO.getAll();
			OrderWorker tOrderWorker = new OrderWorker(1, 2, "N");
			tOrderWorker = orderWorkerDAO.createFrom(tOrderWorker);
			List<OrderWorker> orderWorkerListPost = orderWorkerDAO.getAll();
			tOrderWorker.setIsCompleted("Y");
			orderWorkerDAO.update(tOrderWorker);
			orderWorkerList = orderWorkerDAO.getAll();
			orderWorkerDAO.delete(tOrderWorker);
			orderWorkerList = orderWorkerDAO.getAll();
			//Part
			PartDAO partDAO = (PartDAO) daoFactory.getDao(conn, Part.class);
			List<Part> partList = partDAO.getAll();
			Part tPart = partDAO.create();
			List<Part> partListPost = partDAO.getAll();
			tPart.setAmount(10);
			tPart.setPartName("Test Part");
			tPart.setVendor("Test Vendor");
			partDAO.update(tPart);
			partList = partDAO.getAll();
			partDAO.delete(tPart);
			partList = partDAO.getAll();
			//Status
			StatusDAO statusDAO = (StatusDAO) daoFactory.getDao(conn, Status.class);
			List<Status> statusList = statusDAO.getAll();
			Status tStatus = statusDAO.create();
			List<Status> statusListPost = statusDAO.getAll();
			tStatus.setStatusName("Test Status");
			statusDAO.update(tStatus);
			statusList = statusDAO.getAll();
			statusDAO.delete(tStatus);
			statusList = statusDAO.getAll();
			//Worker
			WorkerDAO workerDAO = (WorkerDAO) daoFactory.getDao(conn, Worker.class);
			List<Worker> workerList = workerDAO.getAll();
			Worker tWorker = workerDAO.create();
			List<Worker> workerListPost = workerDAO.getAll();
			tWorker.setFirstName("Test First Name");
			tWorker.setLastName("Test Last Name");
			tWorker.setSpecializationId(1);
			workerDAO.update(tWorker);
			workerList = workerDAO.getAll();
			workerDAO.delete(tWorker);
			workerList = workerDAO.getAll();
			//WorkerSpecialization
			WorkerSpecializationDAO workerSpecDAO = (WorkerSpecializationDAO) daoFactory.getDao(conn, WorkerSpecialization.class);
			List<WorkerSpecialization> workerSpecList = workerSpecDAO.getAll();
			WorkerSpecialization tSpec = workerSpecDAO.create();
			List<WorkerSpecialization> workerSpecListPost = workerSpecDAO.getAll();
			tSpec.setSpecName("Test Spec Name");
			workerSpecDAO.update(tSpec);
			workerSpecList = workerSpecDAO.getAll();
			workerSpecDAO.delete(tSpec);
			workerSpecList = workerSpecDAO.getAll();
			//WorkerStatus
			WorkerStatusDAO workerStatusDAO = (WorkerStatusDAO) daoFactory.getDao(conn, WorkerStatus.class);
			List<WorkerStatus> workerStatusList = workerStatusDAO.getAll();
			//create worker to create status for him
			Worker workerForStatus = new Worker("tName", "tLastName", 1);
			workerForStatus = workerDAO.createFrom(workerForStatus);
			//
			WorkerStatus tWorkerStatus = new WorkerStatus(workerForStatus.getId(), 2);
			tWorkerStatus = workerStatusDAO.createFrom(tWorkerStatus);
			List<WorkerStatus> workerStatusListPost = workerStatusDAO.getAll();
			tWorkerStatus.setStatusId(3);
			workerStatusDAO.update(tWorkerStatus);
			workerStatusList = workerStatusDAO.getAll();
			workerStatusDAO.delete(tWorkerStatus);
			workerStatusList = workerStatusDAO.getAll();
			//now delete this worker
			workerDAO.delete(workerForStatus);
		} catch (PersistenceException ex) {
			LOG.error(ex);
		}
	}

}
