package com.nixsolutions.app;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.WorkerSpecializationDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderPart;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.Status;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.entity.WorkerSpecialization;

public class DAOApp {

	public static Logger LOG = LogManager.getLogger(DAOApp.class.getName());

	public static void main(String[] args) throws ClassNotFoundException {
		DAOFactory daoFactory = new DAOFactoryImpl();
		// Customer
		CustomerDAO customerDAO = daoFactory.getCustomerDAO();
		List<Customer> customerList = customerDAO.getAll();
		Customer tCustomer = customerDAO.create();
		List<Customer> customerListPost = customerDAO.getAll();
		tCustomer.setFirstName("Test");
		tCustomer.setLastName("Testson");
		tCustomer.setPhone("Nokia");
		customerDAO.update(tCustomer);
		customerList = customerDAO.getAll();
		// Part
		PartDAO partDAO = daoFactory.getPartDAO();
		List<Part> partList = partDAO.getAll();
		Part tPart = partDAO.create();
		List<Part> partListPost = partDAO.getAll();
		tPart.setAmount(10);
		tPart.setPartName("Test Part");
		tPart.setVendor("Test Vendor");
		partDAO.update(tPart);
		partList = partDAO.getAll();
		// WorkerSpecialization
		WorkerSpecializationDAO workerSpecDAO = daoFactory.getWorkerSpecializationDAO();
		List<WorkerSpecialization> workerSpecList = workerSpecDAO.getAll();
		WorkerSpecialization tSpec = workerSpecDAO.create();
		List<WorkerSpecialization> workerSpecListPost = workerSpecDAO.getAll();
		tSpec.setSpecName("Test Spec Name");
		workerSpecDAO.update(tSpec);
		workerSpecList = workerSpecDAO.getAll();
		// Status
		StatusDAO statusDAO = daoFactory.getStatusDAO();
		List<Status> statusList = statusDAO.getAll();
		Status tStatus = statusDAO.create();
		List<Status> statusListPost = statusDAO.getAll();
		tStatus.setStatusName("Test Status");
		statusDAO.update(tStatus);
		statusList = statusDAO.getAll();
		// OrderStatus
		OrderStatusDAO orderStatusDAO = daoFactory.getOrderStatusDAO();
		List<OrderStatus> orderStatusList = orderStatusDAO.getAll();
		OrderStatus tOrderStatus = orderStatusDAO.create();
		List<OrderStatus> orderStatusListPost = orderStatusDAO.getAll();
		tOrderStatus.setOrderStatusName("Test Status");
		orderStatusDAO.update(tOrderStatus);
		orderStatusList = orderStatusDAO.getAll();
		// Car
		CarDAO carDAO = daoFactory.getCarDAO();
		List<Car> carList = carDAO.getAll();
		Car tCar = carDAO.create();
		List<Car> carListPost = carDAO.getAll();
		tCar.setCustomerId(tCustomer.getId());
		tCar.setDescription("Test Description");
		tCar.setModel("Audi Unknown");
		tCar.setVin("12345678901112131");
		carDAO.update(tCar);
		carList = carDAO.getAll();
		// Worker
		WorkerDAO workerDAO = daoFactory.getWorkerDAO();
		List<Worker> workerList = workerDAO.getAll();
		Worker tWorker = workerDAO.create();
		List<Worker> workerListPost = workerDAO.getAll();
		tWorker.setFirstName("Test First Name");
		tWorker.setLastName("Test Last Name");
		tWorker.setSpecializationId(tSpec.getId());
		tWorker.setStatusId(tStatus.getId());
		workerDAO.update(tWorker);
		workerList = workerDAO.getAll();
		// OrderInWork
		OrderInWorkDAO orderDAO = daoFactory.getOrderInWorkDAO();
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
		// OrderPart
		OrderPartDAO orderPartDAO = daoFactory.getOrderPartDAO();
		List<OrderPart> orderPartList = orderPartDAO.getAll();
		OrderPart tOrderPart = new OrderPart(tOrder.getId(), tPart.getId(), 20);
		tOrderPart = orderPartDAO.createFrom(tOrderPart);
		List<OrderPart> orderPartListPost = orderPartDAO.getAll();
		tOrderPart.setUsedAmount(10);
		orderPartDAO.update(tOrderPart);
		orderPartList = orderPartDAO.getAll();
		// OrderWorker
		OrderWorkerDAO orderWorkerDAO = daoFactory.getOrderWorkerDAO();
		List<OrderWorker> orderWorkerList = orderWorkerDAO.getAll();
		OrderWorker tOrderWorker = new OrderWorker(tOrder.getId(), tWorker.getId(), "N");
		tOrderWorker = orderWorkerDAO.createFrom(tOrderWorker);
		List<OrderWorker> orderWorkerListPost = orderWorkerDAO.getAll();
		tOrderWorker.setIsCompleted("Y");
		orderWorkerDAO.update(tOrderWorker);
		orderWorkerList = orderWorkerDAO.getAll();

		// now delete all entities
		orderWorkerDAO.delete(tOrderWorker);
		orderWorkerList = orderWorkerDAO.getAll();
		orderPartDAO.delete(tOrderPart);
		orderPartList = orderPartDAO.getAll();
		orderDAO.delete(tOrder);
		orderList = orderDAO.getAll();
		workerDAO.delete(tWorker);
		workerList = workerDAO.getAll();
		carDAO.delete(tCar);
		carList = carDAO.getAll();
		orderStatusDAO.delete(tOrderStatus);
		orderStatusList = orderStatusDAO.getAll();
		statusDAO.delete(tStatus);
		statusList = statusDAO.getAll();
		workerSpecDAO.delete(tSpec);
		workerSpecList = workerSpecDAO.getAll();
		partDAO.delete(tPart);
		partList = partDAO.getAll();
		customerDAO.delete(tCustomer);
		customerList = customerDAO.getAll();
	}

}
