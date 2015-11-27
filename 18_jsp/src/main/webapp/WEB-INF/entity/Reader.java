package entity;

public class Reader {
	public Reader() {
	}

	public Reader(String name, String adress) {
		this.name = name;
		this.adress = adress;
	}
	public Reader(int id, String name, String adress) {
		this.id = id;
		this.name = name;
		this.adress = adress;
	}
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
}
