package entities;

public class Worker extends AbstractEntity {

	private int worker_id;
	private String first_name;
	private String last_name;
	private int specialization_id;
	
	@Override
	public int getId() {
		return worker_id;
	}

	@Override
	public String getValuesCommaSeparated() {
		return "'" + first_name + "', '" + last_name + "', " + (specialization_id == 0 ? "null" : specialization_id);
	}
	
	public String getFirstName() {
		return first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public int getSpecId() {
		return specialization_id;
	}
	
	protected void setId(int value) {
		worker_id = value;
	}
	
	public void setFirstName(String value) {
		first_name = value;
	}
	
	public void setLastName(String value) {
		last_name = value;
	}
	
	public void setSpecializationId(int value) {
		specialization_id = value;
	}
	
	@Override
	public String toString() {
		String res = "first_name = '" + first_name + "', last_name = '" + 
				last_name + "', specialization_id = " + 
				(specialization_id == 0 ? "null" : specialization_id);
		return res;
	}
	
	public Worker(String first_name, String last_name, int specialization_id) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.specialization_id = specialization_id;
	}
	
	public Worker() {
		this("", "", 0);
	}
}
