package entities;

public class OrderStatus extends AbstractEntity {
	
	private int order_status_id;
	private String order_status_name;
	
	@Override
	public int getId() {
		return order_status_id;
	}

	@Override
	public String getValuesCommaSeparated() {
		return "'" + order_status_name + "'";
	}
	
	public String getOrderStatusName() {
		return order_status_name;
	}
	
	protected void setId(int value) {
		order_status_id = value;
	}
	
	public void setOrderStatusName(String value) {
		order_status_name = value;
	}
	
	@Override
	public String toString() {
		String res = "order_status_name = '" + order_status_name + "'";
		return res;
	}
	
	public OrderStatus(String orderStatusName) {
		this.order_status_name = orderStatusName;
	}
	
	public OrderStatus() {
		this("");
	}
}
