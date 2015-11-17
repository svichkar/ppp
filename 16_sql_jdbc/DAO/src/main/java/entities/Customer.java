package entities;

public class Customer extends BaseEntity {

	private int customer_id;
	private String f_name;
	private String l_name;
	private String phone;

	public Customer(String f_name, String l_name, String phone) {
		this.f_name = f_name;
		this.l_name = l_name;
		this.phone = phone;
	}

	public Customer() {

	}

	public int getId() {
		// TODO Auto-generated method stub
		return customer_id;
	}

	public String getF_name() {
		return f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public String getPhone() {
		return phone;
	}

	protected void setId(int value) {
		customer_id = value;
	}

	public void setf_name(String value) {
		f_name = value;
	}

	public void setl_name(String value) {
		l_name = value;
	}

	public void setphone(String value) {
		phone = value;
	}

}
