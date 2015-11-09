package entities;

public class WorkerStatus extends AbstractEntity {
	
	private int worker_id;
	private int status_id;
	
	@Override
	public int getId() {
		return worker_id;
	}

	@Override
	public String getValuesCommaSeparated() {
		return (worker_id == 0 ? null : String.valueOf(worker_id)) + ", " + 
				(status_id == 0 ? "null" : String.valueOf(status_id));
	}
	
	public int getStatusId() {
		return status_id;
	}
	
	public void setId(int value) {
		worker_id = value;
	}
	
	public void setStatusId(int value) {
		status_id = value;
	}
	
	@Override
	public String toString() {
		String res = "worker_id = " + (worker_id == 0 ? "null" : worker_id) + 
				", status_id = " + (status_id == 0 ? "null" : status_id);
		return res;
	}
	
	public WorkerStatus(int worker_id, int status_id) {
		this.worker_id = worker_id;
		this.status_id = status_id;
	}
	
	public WorkerStatus() {
		this(0, 0);
	}
}
