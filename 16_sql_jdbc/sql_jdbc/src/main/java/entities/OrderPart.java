package entities;

public class OrderPart extends AbstractEntity {
	
	private int order_id;
	private int part_id;
	private int used_amount;

	@Override
	public int getId() {
		return order_id;
	}

	@Override
	public String getValuesCommaSeparated() {
		return (order_id == 0 ? "null" : order_id) + ", " + 
				(part_id == 0 ? "null" : part_id) + ", " + used_amount;
	}
	
	public int getPartId() {
		return part_id;
	}
	
	public int getUsedAmount() {
		return used_amount;
	}
	
	protected void setId(int value) {
		order_id = value;
	}
	
	protected void setPartId(int value) {
		part_id = value;
	}
	
	public void setUsedAmount(int value) {
		used_amount = value;
	}
	
	@Override
	public String toString() {
		String res = "used_amount = " + used_amount;
		return res;
	}
	
	public OrderPart(int order_id, int part_id, int used_amount) {
		this.order_id = order_id;
		this.part_id = part_id;
		this.used_amount = used_amount;
	}
	
	public OrderPart() {
		this(0, 0, 0);
	}
}
