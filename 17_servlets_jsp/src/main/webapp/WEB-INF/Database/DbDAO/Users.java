package Database.DbDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.Factory;

public class Users extends Factory {

	private int id;
	private String login;
	private String password;
	private String role;

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Users> findElement(String[] searchField, String searchQuery)
			throws SQLException {
		List<Users> cat = new ArrayList<Users>();
		ResultSet re = super.find(searchField, searchQuery);

		List<String> columnNames = new ArrayList<String>();
		int columnCount = re.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(re.getMetaData().getColumnName(i).toLowerCase());
		}

		while (re.next()) {
			Users r = new Users();
			if (columnNames.contains("id"))
				r.id = re.getInt("id");
			if (columnNames.contains("login"))
				r.login = re.getString("login");
			if (columnNames.contains("password"))
				r.password = re.getString("password");
			if (columnNames.contains("role"))
				r.role = re.getString("role");

			cat.add(r);

		}
		return cat;
	}

}
