package entities;

public class OrderWorker extends AbstractEntity {
	
	private int order_id;
	private int worker_id;
	private String is_completed;

	@Override
	public int getId() {
		return order_id;
	}

	@Override
	public String getValuesCommaSeparated() {
		return (order_id == 0 ? "null" : order_id) + ", " + 
	(worker_id == 0 ? "null" : worker_id) + ", '" + is_completed + "'";
	}
	
	public int getWorkerId() {
		return worker_id;
	}
	
	public String getIsCompleted() {
		return is_completed;
	}
	
	protected void setId(int value) {
		order_id = value;
	}
	
	protected void setWorkerId(int value) {
		worker_id = value;
	}
	
	public void setIsCompleted(String value) {
		is_completed = (value == "Y" ? value : "N");
	}
	
	@Override
	public String toString() {
		//String res = "worker_id " + (worker_id == 0 ? "is null" : "= " + worker_id) + " AND is_completed = '" + is_completed + "'";
		String res = "is_completed = '" + is_completed + "'";
		return res;
	}
	
	public OrderWorker(int order_id, int worker_id, String is_completed) {
		this.order_id = order_id;
		this.worker_id = worker_id;
		setIsCompleted(is_completed);
	}
	
	public OrderWorker() {
		this(0, 0, "N");
	}
}
