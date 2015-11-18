package Database.DbDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.Factory;

public class Journal extends Factory {

	private int id;
	private int book_id;
	private int reader_id;
	private Date start_date;
	private Date end_date;

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
	public int getReader_id() {
		return reader_id;
	}
	public void setReader_id(int reader_id) {
		this.reader_id = reader_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public List<Journal> findElement(String[] searchField, String searchQuery)
			throws SQLException {
		List<Journal> cat = new ArrayList<Journal>();
		ResultSet re = super.find(searchField, searchQuery);
		List<String> columnNames = new ArrayList<String>();
		int columnCount = re.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(re.getMetaData().getColumnName(i).toLowerCase());
		}

		while (re.next()) {
			Journal j = new Journal();
			if (columnNames.contains("id"))
				j.id = re.getInt("id");
			if (columnNames.contains("book_id"))
				j.book_id = re.getInt("book_id");
			if (columnNames.contains("reader_id"))
				j.reader_id = re.getInt("reader_id");
			if (columnNames.contains("start_date"))
				j.start_date = re.getDate("start_date");
			if (columnNames.contains("end_date"))
				j.end_date = re.getDate("end_date");
			cat.add(j);

		}
		return cat;

	}
}
