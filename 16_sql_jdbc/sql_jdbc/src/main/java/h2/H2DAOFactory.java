package h2;

import java.io.File;
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
import entities.WorkerStatus;

public class H2DAOFactory implements DAOFactory<Connection> {
	
	public static Logger LOG = LogManager.getLogger(H2DAOFactory.class.getName());
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
        return  connection;
	}

	@Override
	public GenericDAO<?> getDao(Connection connection, Class<?> dtoClass) throws PersistenceException {
		DAOCreator<Connection> creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistenceException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
	}
	
	public H2DAOFactory() throws ClassNotFoundException {
		Class.forName(driver);
		Properties props = new Properties();
		String projDir = System.getProperty("user.dir");
		String curSeparator = File.separator;
		String propsLocation = projDir + curSeparator + "src" + curSeparator + "main" + 
		curSeparator + "resources" + curSeparator + "jdbc.properties";
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
                return new CarDAO(connection);
            }
        });
        creators.put(Customer.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Customer> create(Connection connection) {
                return new CustomerDAO(connection);
            }
        });
        creators.put(OrderInWork.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<OrderInWork> create(Connection connection) {
                return new OrderInWorkDAO(connection);
            }
        });
        creators.put(OrderPart.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<OrderPart> create(Connection connection) {
                return new OrderPartDAO(connection);
            }
        });
        creators.put(OrderStatus.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<OrderStatus> create(Connection connection) {
                return new OrderStatusDAO(connection);
            }
        });
        creators.put(OrderWorker.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<OrderWorker> create(Connection connection) {
                try {
					return new OrderWorkerDAO(connection);
				} catch (PersistenceException e) {					
					LOG.error(e.getMessage());
					return null;
				}
            }
        });
        creators.put(Part.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Part> create(Connection connection) {
                return new PartDAO(connection);
            }
        });
        creators.put(Status.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Status> create(Connection connection) {
                return new StatusDAO(connection);
            }
        });
        creators.put(Worker.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<Worker> create(Connection connection) {
                return new WorkerDAO(connection);
            }
        });
        creators.put(WorkerSpecialization.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<WorkerSpecialization> create(Connection connection) {
                return new WorkerSpecializationDAO(connection);
            }
        });
        creators.put(WorkerStatus.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO<WorkerStatus> create(Connection connection) {
                try {
					return new WorkerStatusDAO(connection);
				} catch (PersistenceException e) {					
					LOG.error(e.getMessage());
					return null;
				}
            }
        });
    }

}
