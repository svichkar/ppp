package h2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.DAOFactory;
import dao.GenericDAO;
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

public class H2DAOFactoryImpl implements DAOFactory<Connection> {
	
	public static Logger LOG = LogManager.getLogger(H2DAOFactoryImpl.class.getName());
	private String user;
    private String password;
    private String url;
    private String driver = "org.h2.Driver";
    private Map<Class<?>, DAOCreator<Connection>> creators;

	@Override
	public Connection getContext() throws PersistenceException {
		Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return connection;
	}

	@Override
	public GenericDAO<?> getDao(Connection connection, Class<?> dtoClass) throws PersistenceException {
		DAOCreator<Connection> creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistenceException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
	}
	
	public H2DAOFactoryImpl() throws ClassNotFoundException {
		Class.forName(driver);
		Properties props = new Properties();
		String propsLocation = this.getClass().getClassLoader().getResource("jdbc.properties").getFile();
		try (FileInputStream fis = new FileInputStream(propsLocation)) {
			props.load(fis);
			user = props.getProperty("DB_USER");
			password = props.getProperty("DB_PASSWORD");
			url = props.getProperty("DB_DRIVER");
		} catch (IOException ex) {
			//LOG.error(ex.getMessage());
			LOG.catching(ex);
		}
        creators = new HashMap<Class<?>, DAOCreator<Connection>>();
        creators.put(Car.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Car> create(Connection connection) {
                return new CarDAOImpl(connection);
            }
        });
        creators.put(Customer.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Customer> create(Connection connection) {
                return new CustomerDAOImpl(connection);
            }
        });
        creators.put(OrderInWork.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<OrderInWork> create(Connection connection) {
                return new OrderInWorkDAOImpl(connection);
            }
        });
        creators.put(OrderPart.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<OrderPart> create(Connection connection) {
                return new OrderPartDAOImpl(connection);
            }
        });
        creators.put(OrderStatus.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<OrderStatus> create(Connection connection) {
                return new OrderStatusDAOImpl(connection);
            }
        });
        creators.put(OrderWorker.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<OrderWorker> create(Connection connection) {
                try {
					return new OrderWorkerDAOImpl(connection);
				} catch (PersistenceException e) {					
					LOG.error(e.getMessage());
					return null;
				}
            }
        });
        creators.put(Part.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Part> create(Connection connection) {
                return new PartDAOImpl(connection);
            }
        });
        creators.put(Status.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Status> create(Connection connection) {
                return new StatusDAOImpl(connection);
            }
        });
        creators.put(Worker.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Worker> create(Connection connection) {
                return new WorkerDAOImpl(connection);
            }
        });
        creators.put(WorkerSpecialization.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<WorkerSpecialization> create(Connection connection) {
                return new WorkerSpecializationDAOImpl(connection);
            }
        });
    }

}
