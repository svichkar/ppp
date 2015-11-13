package com.jdbcDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reader extends Factory {

	private int id;
	private String name;
	private String adress;

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

			cat.add(r);

		}
		return cat;
	}

}
