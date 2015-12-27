package com.nixsolutions.h2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.GenericDAO;
import com.nixsolutions.entities.PersistenceException;
import com.nixsolutions.entities.Role;
import com.nixsolutions.entities.User;

public class H2DAOFactoryImpl implements DAOFactory<Connection> {

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

	public H2DAOFactoryImpl() throws ClassNotFoundException, IOException {
		Class.forName(driver);
		Properties props = new Properties();
		String propsLocation = this.getClass().getClassLoader().getResource("jdbc.properties").getFile().replaceAll("%20", " ");
		FileInputStream fis = new FileInputStream(propsLocation);
		props.load(fis);
		user = props.getProperty("DB_USER");
		password = props.getProperty("DB_PASSWORD");
		fis.close();
		String dbLocation = this.getClass().getClassLoader().getResource("sqllab.mv.db").getFile();
		url = "jdbc:h2:file:" + dbLocation.replaceAll("%20", " ").replaceAll(".mv.db", "").replaceAll("/(\\w:)", "$1");

		creators = new HashMap<Class<?>, DAOCreator<Connection>>();
		creators.put(User.class, new DAOCreator<Connection>() {
			@Override
			public GenericDAO<User> create(Connection connection) {
				return new UserDAOImpl(connection);
			}
		});
		creators.put(Role.class, new DAOCreator<Connection>() {
			@Override
			public GenericDAO<Role> create(Connection connection) {
				return new RoleDAOImpl(connection);
			}
		});
	}

}
