package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.GenericDao;
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

public class ServiceStationDAOFactoryImpl implements DaoFactory<Connection> {

	private Map<Class<?>, CreateDAO> mapWithCreator;

	@Override
	public GenericDao<?> getDao(Class<?> doClass) throws Exception {

		CreateDAO creator = mapWithCreator.get(doClass);
		if (creator == null) {
			throw new Exception("Dao object for " + doClass + " not found.");
		}
		return creator.create();
	}

	public ServiceStationDAOFactoryImpl() {
		mapWithCreator = new HashMap<Class<?>, CreateDAO>();

		mapWithCreator.put(Car.class, new CreateDAO() {

			@Override
			public GenericDao<Car> create() {
				return new CarDAOImpl();
			}

		});

		mapWithCreator.put(Customer.class, new CreateDAO() {

			@Override
			public GenericDao<Customer> create() {
				return new CustomerDAOImpl();
			}
		});
		mapWithCreator.put(OrderInWork.class, new CreateDAO() {

			@Override
			public GenericDao<OrderInWork> create() {
				return new OrderInWorkDAOImpl();
			}
		});
		mapWithCreator.put(OrderStatus.class, new CreateDAO() {

			@Override
			public GenericDao<?> create() {
				return new OrderStatusDAOImpl();
			}
		});
		mapWithCreator.put(OrderWorker.class, new CreateDAO() {

			@Override
			public GenericDao<OrderWorker> create() {
				return new OrderWorkerDAOImpl();
			}
		});
		mapWithCreator.put(Part.class, new CreateDAO() {

			@Override
			public GenericDao<Part> create() {
				return new PartDAOImpl();
			}
		});
		mapWithCreator.put(PartOrder.class, new CreateDAO() {

			@Override
			public GenericDao<PartOrder> create() {
				return new PartOrderDAOImpl();
			}
		});
		mapWithCreator.put(Status.class, new CreateDAO() {

			@Override
			public GenericDao<Status> create() {
				return new StatusDAOImpl();
			}
		});
		mapWithCreator.put(Worker.class, new CreateDAO() {

			@Override
			public GenericDao<Worker> create() {
				return new WorkerDAOImpl();
			}
		});
		mapWithCreator.put(WorkerSpecification.class, new CreateDAO() {

			@Override
			public GenericDao<WorkerSpecification> create() {
				return new WorkerSpecificationDAOImpl();
			}
		});
	}
}
