package com.jdbcDAO;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInstance extends Factory {

	private int id;
	private int book_id;
	private int inventory_number;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getInventory_number() {
		return inventory_number;
	}
	public void setInventory_number(int inventory_number) {
		this.inventory_number = inventory_number;
	}

	public List<BookInstance> findElement(String[] searchField,
			String searchQuery) throws SQLException {
		List<BookInstance> cat = new ArrayList<BookInstance>();
		ResultSet re = super.find(searchField, searchQuery);
		List<String> columnNames = new ArrayList<String>();
		int columnCount = re.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(re.getMetaData().getColumnName(i).toLowerCase());
		}

		while (re.next()) {
			BookInstance bi = new BookInstance();
			if (columnNames.contains("id"))
				bi.id = re.getInt("id");
			if (columnNames.contains("book_id"))
				bi.book_id = re.getInt("book_id");
			if (columnNames.contains("inventory_number"))
				bi.inventory_number = re.getInt("inventory_number");
			cat.add(bi);

		}
		return cat;
	}

}
