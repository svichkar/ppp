package Database.DbDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.Factory;

public class Book extends Factory {

	private int id;
	private String name;
	private String author;
	private String publisher;
	private int category_id;

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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public List<Book> findElement(String[] searchField, String searchQuery)
			throws SQLException {
		List<Book> cat = new ArrayList<Book>();
		ResultSet re = super.find(searchField, searchQuery);
		List<String> columnNames = new ArrayList<String>();
		int columnCount = re.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(re.getMetaData().getColumnName(i).toLowerCase());
		}

		while (re.next()) {
			Book b = new Book();
			if (columnNames.contains("id"))
				b.id = re.getInt("id");
			if (columnNames.contains("author"))
				b.author = re.getString("author");
			if (columnNames.contains("publisher"))
				b.publisher = re.getString("publisher");
			if (columnNames.contains("category_id"))
				b.category_id = re.getInt("category_id");
			cat.add(b);

		}
		return cat;
	}
}
