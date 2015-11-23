package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import util.ConnectionManager;

public class DropAllTablesFromDB {
	static final Logger LOG = LogManager.getLogger(DropAllTablesFromDB.class);
	public static void main(String[] args) {

		BasicConfigurator.configure();

		String[] listOfTableNames = new String[]{"category", "book",
				"bookinstance", "journal", "reader"};
		try (Connection connection = ConnectionManager.getInstance()) {

			try (Statement statement = connection.createStatement()) {

				for (String string : listOfTableNames) {
					try {
						statement.execute("DROP TABLE " + string);
						System.out.println("Table " + string + " was droped");
					} catch (Exception e) {
						LOG.error(e, e);
					}

				}
			}
		} catch (SQLException e) {
			LOG.error(e, e);
		}

	}
}
