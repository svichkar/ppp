/**
 * 
 */
package com.nixsolutions.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.CarDao;
import com.nixsolutions.dao.CustomerDao;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.dao.OrderStatusDao;
import com.nixsolutions.dao.OrderWorkerDao;
import com.nixsolutions.dao.PartDao;
import com.nixsolutions.dao.PartOrderDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.dao.WorkerSpecializationDao;
import com.nixsolutions.dao.WorkerStatusDao;
import com.nixsolutions.entity.Worker;

/**
 * @author mixeyes
 *
 */
public class FillingDB {
	private final static Logger logger = LogManager.getLogger();

	/**
	 * @param args
	 * @ @throws
	 *       ClassNotFoundException
	 */
	public static void main(String[] args) {
		fillingDB();
	}

	public static void fillingDB() {

		/* sqllab.customer */
		UserRoleDao roleDaoImpl = DaoFactory.getUserRoleDao();
		roleDaoImpl.createNewUserRole("manager");
		roleDaoImpl.createNewUserRole("customer");
		roleDaoImpl.createNewUserRole("worker");
		roleDaoImpl.createNewUserRole("storekeeper");

		UserDao userDaoImpl = DaoFactory.getUserDao();
		userDaoImpl.createNewUser("manager", "manager", 1);
		userDaoImpl.createNewUser("customer1", "customer", 2);
		userDaoImpl.createNewUser("customer2", "customer", 2);
		userDaoImpl.createNewUser("customer3", "customer", 2);
		userDaoImpl.createNewUser("customer4", "customer", 2);
		userDaoImpl.createNewUser("customer5", "customer", 2);
		userDaoImpl.createNewUser("worker", "worker", 3);
		userDaoImpl.createNewUser("storekeeper", "storekeeper", 4);
		userDaoImpl.createNewUser("worker1", "worker", 3);
		userDaoImpl.createNewUser("worker2", "worker", 3);
		userDaoImpl.createNewUser("worker3", "worker", 3);

		CustomerDao customerDaoImpl = DaoFactory.getCustomerDao();
		customerDaoImpl.createNewCustomer("IVANOV", "IVAN", "1-23-3455-5435", 2);
		customerDaoImpl.createNewCustomer("Petrov", "Petr", "23-3455-5435", 3);
		customerDaoImpl.createNewCustomer("Fedorov", "Fedor", "1-23-3454735863", 4);
		customerDaoImpl.createNewCustomer("Alkov", "Alex", "827382755-5435", 5);
		customerDaoImpl.createNewCustomer("Alexov", "Alk", "82755-54827335", 6);

		/* sqllab.car */
		CarDao carDaoImpl = DaoFactory.getCarDao();
		carDaoImpl.createNewCar("AUDI", "u45890qwe67123rty", "forever first auto for tests", "AX1234AA", 1);
		carDaoImpl.createNewCar("BMW", "u4583rty90qwe6712", "TAZ", "AX1256AC", 2);
		carDaoImpl.createNewCar("BMW", "e67u45890qw123rty", "TAZ", "AX4356EE", 2);
		carDaoImpl.createNewCar("OPEL", "u45812390qwe67rty", "car_description", "AX7634EA", 3);
		carDaoImpl.createNewCar("ВАЗ-2102", "u453rt890qwe6712y", "car_description", "AX0923BC", 2);
		carDaoImpl.createNewCar("DODGE CHARGER", "u4581290qwe673rty", "car_description", "AX8623BB", 4);

		/* sqllab.worker_specialization */
		WorkerSpecializationDao specializationDaoImpl = DaoFactory.getWorkerSpecializationDao();
		specializationDaoImpl.createNewSpecialization("mechanik low");
		specializationDaoImpl.createNewSpecialization("mechanik high");
		specializationDaoImpl.createNewSpecialization("electric");
		specializationDaoImpl.createNewSpecialization("diagnostician");
		specializationDaoImpl.createNewSpecialization("engine");
		specializationDaoImpl.createNewSpecialization("manager");
		specializationDaoImpl.createNewSpecialization("storekeeper");

		/* sqllab.worker_status */
		WorkerStatusDao workerStatusDaoImpl = DaoFactory.getWorkerStatusDao();
		workerStatusDaoImpl.createNewStatus("busy");
		workerStatusDaoImpl.createNewStatus("free");
		workerStatusDaoImpl.createNewStatus("ill");
		workerStatusDaoImpl.createNewStatus("vacation");

		/* sqllab.worker */
		WorkerDao workerDaoImpl = DaoFactory.getWorkerDao();
		Worker worker = new Worker(1, "Ivanov", "Ivan", 1, 1);
		workerDaoImpl.createWorker(worker);
		worker = new Worker(1, "Ivanov", "Petr", 2, 6);
		workerDaoImpl.createWorker(worker);
		worker = new Worker(2, "Ivanov", "Fedya", 3, 7);
		workerDaoImpl.createWorker(worker);
		worker = new Worker(3, "Ivanov", "Anton", 2, 8);
		workerDaoImpl.createWorker(worker);
		worker = new Worker(4, "Ivanov", "Aleksey", 4, 9);
		workerDaoImpl.createWorker(worker);
		worker = new Worker(5, "Ivanov", "Evgeniy", 1, 10);
		workerDaoImpl.createWorker(worker);
		worker = new Worker(4, "Vasiliev", "Vasia", 2, 11);
		workerDaoImpl.createWorker(worker);

		/* sqllab.part */
		PartDao partDaoImpl = DaoFactory.getPartDao();
		partDaoImpl.addNewPart("tyaga ВАЗ", "81-56fa354fa", 6);
		partDaoImpl.addNewPart("brake pads OPEL", "5634-fafa815", 12);
		partDaoImpl.addNewPart("brake pads BMW", "54-fafa81563", 12);
		partDaoImpl.addNewPart("tyaga BMW", "8156fa354-fa", 6);
		partDaoImpl.addNewPart("shrovaya BMW", "8156354-fafa", 5);
		partDaoImpl.addNewPart("brake pads AUDI", "54-fafa81563", 12);
		partDaoImpl.addNewPart("tyaga AUDI", "8156fa354-fa", 6);
		partDaoImpl.addNewPart("lamp H4 PHILIPS", "6354-f815afa", 10);
		partDaoImpl.addNewPart("lamp H4 OSRAM", "81a56354-faf", 10);
		partDaoImpl.addNewPart("shrovaya AUDI", "8156354-fafa", 5);

		/* sqllab.order_status */
		OrderStatusDao orderStatusDaoImpl = DaoFactory.getOrderStatusDao();
		orderStatusDaoImpl.createNewStatus("waiting");
		orderStatusDaoImpl.createNewStatus("in work");
		orderStatusDaoImpl.createNewStatus("ready");

		/* sqllab.order_in_work */
		OrderInWorkDao orderInWorkDaoImpl = DaoFactory.getOrderInWorkDao();
		orderInWorkDaoImpl.createNewOrder(1, "change lamp");
		orderInWorkDaoImpl.createNewOrder(2, "change brake pads");
		orderInWorkDaoImpl.changeOrderStatusByOrderID(2, 2);
		orderInWorkDaoImpl.createNewOrder(5, "change brake pads");
		orderInWorkDaoImpl.changeOrderStatusByOrderID(3, 3);
		orderInWorkDaoImpl.createNewOrder(4, "change tyaga");
		orderInWorkDaoImpl.changeOrderStatusByOrderID(4, 3);

		PartOrderDao partOrderDaoImpl = DaoFactory.getPartOrderDao();
		partOrderDaoImpl.setPartToOrder(1, 3, 2);
		partOrderDaoImpl.setPartToOrder(2, 8, 4);
		partOrderDaoImpl.setPartToOrder(2, 3, 2);
		partOrderDaoImpl.setPartToOrder(4, 10, 1);

		OrderWorkerDao orderWorkerDaoImpl = DaoFactory.getOrderWorkerDao();
		orderWorkerDaoImpl.assignWorkerToOrder(1, 2);

		logger.trace("db was filling");
	}
}
