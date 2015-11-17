package entities;

public class Worker extends BaseEntity {

	private int worker_id;
	private String f_name;
	private String l_name;
	private int spec_id;
	private int status_id;

	public Worker(String f_name, String l_name, int spec_id, int status_id) {
		this.f_name = f_name;
		this.l_name = l_name;
		this.spec_id = spec_id;
		this.status_id = status_id;
	}
	
	public Worker() {
		
	}

	public int getId() {
		return worker_id;
	}

	public String getF_name() {
		return f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public int getSpec_id() {
		return spec_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	protected void setId(int value) {
		worker_id = value;
	}

	public void setF_name(String value) {
		f_name = value;
	}

	public void setL_name(String value) {
		l_name = value;
	}

	public void setSpec_id(int value) {
		spec_id = value;
	}

	public void setStatus_id(int value) {
		status_id = value;
	}
}
