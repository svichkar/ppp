package com.nixsolutions.entities;

public class PartOrder extends BaseEntity {

	private int order_id;
	private int part_id;
	private int amount;
	
	public PartOrder(int order_id, int part_id, int amount)
	{
		this.order_id = order_id;
		this.part_id = part_id;
		this.amount = amount;
	}
	
	public PartOrder()
	{
		
	}

	public int getId() {
		return order_id;
	}
	
	public int getPartid()
	{
		return part_id;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setId(int value) {
		order_id = value;
	}
	
	public void  setPartid(int value)
	{
		part_id = value;
	}
	
	public void setAmount(int value)
	{
		amount = value;
	}
	
}
