package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.nixsolutions.bean.AllPartsInOrderBean;
import com.nixsolutions.bean.AllWorkersInOrderBean;
import com.nixsolutions.bean.CarCustomerBean;
import com.nixsolutions.bean.OrderInWorkCarStatusBean;
import com.nixsolutions.bean.UserCustomerRoleBean;
import com.nixsolutions.bean.WorkerStatusSpecificationBean;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.GenericDao;
import com.nixsolutions.entities.AllPartsInOrder;
import com.nixsolutions.entities.AllWorkersInOrder;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.CarCustomer;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderInWorkCarStatus;
import com.nixsolutions.entities.OrderStatus;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.entities.Part;
import com.nixsolutions.entities.PartOrder;
import com.nixsolutions.entities.Status;
import com.nixsolutions.entities.Worker;
import com.nixsolutions.entities.User;
import com.nixsolutions.entities.UserCustomerRole;
import com.nixsolutions.entities.Role;
import com.nixsolutions.entities.WorkerSpecification;
import com.nixsolutions.entities.WorkerStatusSpecification;

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

		mapWithCreator.put(User.class, new CreateDAO() {

			@Override
			public GenericDao<User> create() {
				return new UserDAOImpl();
			}

		});

		mapWithCreator.put(Role.class, new CreateDAO() {

			@Override
			public GenericDao<Role> create() {
				return new RoleDAOImpl();
			}

		});

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
			public GenericDao<OrderStatus> create() {
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
		mapWithCreator.put(UserCustomerRole.class, new CreateDAO() {

			@Override
			public GenericDao<UserCustomerRole> create() {
				return new UserCustomerRoleBean();
			}
		});
		mapWithCreator.put(CarCustomer.class, new CreateDAO() {

			@Override
			public GenericDao<CarCustomer> create() {
				return new CarCustomerBean();
			}
		});
		mapWithCreator.put(WorkerStatusSpecification.class, new CreateDAO() {

			@Override
			public GenericDao<WorkerStatusSpecification> create() {
				return new WorkerStatusSpecificationBean();
			}
		});
		mapWithCreator.put(OrderInWorkCarStatus.class, new CreateDAO() {

			@Override
			public GenericDao<OrderInWorkCarStatus> create() {
				return new OrderInWorkCarStatusBean();
			}
		});
		mapWithCreator.put(AllPartsInOrder.class, new CreateDAO() {

			@Override
			public GenericDao<AllPartsInOrder> create() {
				return new AllPartsInOrderBean();
			}
		});
		mapWithCreator.put(AllWorkersInOrder.class, new CreateDAO() {

			@Override
			public GenericDao<AllWorkersInOrder> create() {
				return new AllWorkersInOrderBean();
			}
		});

	}
}
