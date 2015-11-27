package entity;

public class Book {

	private int id;
	private String name;
	private String author;
	private String publisher;
	private int category_id;
	private String category_name;
	public Book() {
	}
	public Book(int id, String bookName, String bookAuthor,
			String bookPublisher, int categoryId) {
		this.id = id;
		name = bookName;
		author = bookAuthor;
		publisher = bookPublisher;
		category_id = categoryId;
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
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
}
