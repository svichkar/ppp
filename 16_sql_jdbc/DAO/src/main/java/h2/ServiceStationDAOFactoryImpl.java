package h2;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.nixsolutions.ConnectionManager;

import dao.DaoFactory.*;
import dao.DaoFactory;
import dao.GenericDao;
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

public class ServiceStationDAOFactoryImpl implements DaoFactory<Connection> {

	private Map<Class<?>, CreateDAO<Connection>> mapWithCreator;
	private ConnectionManager connMgr;

	@Override
	public GenericDao<?> getDao(Connection conn, Class<?> doClass)
			throws Exception {

		CreateDAO<Connection> creator = mapWithCreator.get(doClass);
		if (creator == null) {
			throw new Exception("Dao object for " + doClass + " not found.");
		}
		return creator.create(conn);
	}

	public ServiceStationDAOFactoryImpl() throws Exception {
		connMgr = new ConnectionManager();
		mapWithCreator = new HashMap<Class<?>, CreateDAO<Connection>>();

		mapWithCreator.put(Car.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<Car> create(Connection context) throws Exception {
				return new CarDAOImpl(context);
			}

		});
		
		mapWithCreator.put(Customer.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<Customer> create(Connection context)
					throws Exception {
				return new CustomerDAOImpl(context);
			}
		});
		mapWithCreator.put(OrderInWork.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<OrderInWork> create(Connection context)
					throws Exception {
				return new OrderInWorkDAOImpl(context);
			}
		});
		mapWithCreator.put(OrderStatus.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<?> create(Connection context) throws Exception {
				return new OrderStatusDAOImpl(context);
			}
		});
		mapWithCreator.put(OrderWorker.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<OrderWorker> create(Connection context)
					throws Exception {
				return new OrderWorkerDAOImpl(context);
			}
		});
		mapWithCreator.put(Part.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<Part> create(Connection context) throws Exception {
				return new PartDAOImpl(context);
			}
		});
		mapWithCreator.put(PartOrder.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<PartOrder> create(Connection context)
					throws Exception {
				return new PartOrderDAOImpl(context);
			}
		});
		mapWithCreator.put(Status.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<Status> create(Connection context)
					throws Exception {
				return new StatusDAOImpl(context);
			}
		});
		mapWithCreator.put(Worker.class, new CreateDAO<Connection>() {

			@Override
			public GenericDao<Worker> create(Connection context) throws Exception {
				return new WorkerDAOImpl(context);
			}
		});
		mapWithCreator.put(WorkerSpecification.class, new CreateDAO<Connection>() {
			
			@Override
			public GenericDao<WorkerSpecification> create(Connection context) throws Exception {
				return new WorkerSpecificationDAOImpl(context);
			}
		});
	}
}
