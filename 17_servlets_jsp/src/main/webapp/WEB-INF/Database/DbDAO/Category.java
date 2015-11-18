package Database.DbDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.Factory;

public class Category extends Factory {

	private int id;
	private String name;

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

	public List<Category> findElement(String[] searchField, String searchQuery)
			throws SQLException {
		List<Category> cat = new ArrayList<Category>();
		ResultSet re = super.find(searchField, searchQuery);
		List<String> columnNames = new ArrayList<String>();
		int columnCount = re.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(re.getMetaData().getColumnName(i).toLowerCase());
		}
		while (re.next()) {
			Category c = new Category();
			if (columnNames.contains("id"))
				c.id = re.getInt("id");
			if (columnNames.contains("name"))
				c.name = re.getString("name");
			cat.add(c);

		}
		return cat;
	}

}
