package entities;

public class OrderStatus extends BaseEntity {

	private int order_status_id;
	private String order_status_name;

	public OrderStatus(int order_status_id, String order_status_name) {
		this.order_status_id = order_status_id;
		this.order_status_name = order_status_name;
	}

	public OrderStatus() {

	}

	public int getId() {
		return order_status_id;
	}

	public String getOrder_status_name() {
		return order_status_name;
	}

	protected void setId(int value) {
		order_status_id = value;
	}

	public void setOrder_status_name(String value) {
		order_status_name = value;
	}
}
