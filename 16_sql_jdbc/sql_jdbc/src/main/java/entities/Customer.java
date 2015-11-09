package entities;

public class Customer extends AbstractEntity {
	
	private int customer_id;
	private String first_name;
	private String last_name;
	private String phone;
	
	@Override
	public int getId() {
		return customer_id;
	}
	
	@Override
	public String getValuesCommaSeparated() {
		return "'" + first_name + "', '" + last_name + "', '" + phone + "'";
	}
	
	public String getFirstName() {
		return first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	protected void setId(int value) {
		customer_id = value;
	}
	
	public void setFirstName(String value) {
		first_name = value;
	}
	
	public void setLastName(String value) {
		last_name = value;
	}
	
	public void setPhone(String value) {
		phone = value;
	}
	
	@Override
	public String toString() {
		String res = "first_name = '" + first_name + "', last_name = '" + 
	last_name + "', phone = '" + phone + "'";
		return res;
	}
	
	public Customer(String first_name, String last_name, String phone) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
	}
	
	public Customer() {
		this("", "", "null");
	}
}
