package Database.DbDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.Factory;

public class Reader extends Factory {

	private int id;
	private String name;
	private String adress;
	private String login;
	private String password;
	private String role;

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void validatePasswordByLogin() {

		// need to be implemented

	}

	public List<Reader> findElement(String[] searchField, String searchQuery)
			throws SQLException {
		List<Reader> cat = new ArrayList<Reader>();
		ResultSet re = super.find(searchField, searchQuery);

		List<String> columnNames = new ArrayList<String>();
		int columnCount = re.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(re.getMetaData().getColumnName(i).toLowerCase());
		}

		while (re.next()) {
			Reader r = new Reader();
			if (columnNames.contains("id"))
				r.id = re.getInt("id");
			if (columnNames.contains("name"))
				r.name = re.getString("name");
			if (columnNames.contains("adress"))
				r.adress = re.getString("adress");
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
