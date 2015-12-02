package entity;

import java.sql.Date;

public class Journal {
	public Journal() {
	}

	private int id;
	private int book_instance_id;
	private int reader_id;
	private Date start_date;
	private Date end_date;
	private String bookName;
	private String bookAuthor;
	private int bookInventoryNumber;
	private String readerName;
	private String bookCategory;

	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public int getBookInventoryNumber() {
		return bookInventoryNumber;
	}
	public void setBookInventoryNumber(int bookInventoryNumber) {
		this.bookInventoryNumber = bookInventoryNumber;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_instance_id() {
		return book_instance_id;
	}
	public void setBook_instance_id(int book_instance_id) {
		this.book_instance_id = book_instance_id;
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
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
}
