package entities;

public class WorkerSpecialization extends AbstractEntity {

	private int specialization_id;
	private String specialization_name;
	
	@Override
	public int getId() {
		return specialization_id;
	}

	@Override
	public String getValuesCommaSeparated() {
		return "'" + specialization_name + "'";
	}
	
	public String getSpecName() {
		return specialization_name;
	}
	
	protected void setId(int value) {
		specialization_id = value;
	}
	
	public void setSpecName(String value) {
		specialization_name = value;
	}
	
	@Override
	public String toString() {
		String res = "specialization_name = '" + specialization_name + "'";
		return res;
	}
	
	public WorkerSpecialization(String specName) {
		this.specialization_name = specName;
	}
	
	public WorkerSpecialization() {
		this("");
	}
}
