package Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {
	private static Properties prop;
	private static JdbcConnectionPool instance = null;
	private static String filename = "Database/jdbc.properties";

	public static JdbcConnectionPool getInstance() {

		InputStream input = null;
		if (instance == null) {
			prop = new Properties();

			try {
				ClassLoader classLoader = ConnectionManager.class
						.getClassLoader();
				input = new FileInputStream(classLoader.getResource("")
						.getPath().toString().replace("%20", " ") + filename);

				prop.load(input);
				instance = JdbcConnectionPool.create(prop.getProperty("HOST"),
						prop.getProperty("LOGIN"),
						prop.getProperty("PASSWORD"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return instance;
	}

}
