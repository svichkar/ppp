package com.nixsolutions.entity;

import java.sql.Timestamp;

public class OrderInWork {
	
	private int order_id;
	private int order_status_id;
	private String description;
	private int car_id;
	private Timestamp timestamp_start;
	private Timestamp timestamp_finish;

	public int getId() {
		return order_id;
	}

	public String getValuesCommaSeparated() {
		return (order_status_id == 0 ? "null" : order_status_id) + ", '" + 
				description + "', " + (car_id == 0 ? "null" : car_id) + ", " + 
				(timestamp_start == null ? "current_timestamp()" : "PARSEDATETIME('" + 
				timestamp_start.toString() + "', 'yyyy-MM-dd HH:mm:ss.SS')") + 
				", " + (timestamp_finish == null ? "null" : "PARSEDATETIME('" + 
				timestamp_finish.toString() + "', 'yyyy-MM-dd HH:mm:ss.SS')");
	}
	
	public int getOrderStatusId() {
		return order_status_id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getCarId() {
		return car_id;
	}
	
	public Timestamp getTimestampStart() {
		return timestamp_start;
	}
	
	public Timestamp getTimestampFinish() {
		return timestamp_finish;
	}
	
	public void setId(int value) {
		order_id = value;
	}
	
	public void setOrderStatusId(int value) {
		order_status_id = value;
	}
	
	public void setDescription(String value) {
		description = value;
	}
	
	public void setCarId(int value) {
		car_id = value;
	}
	
	public void setTimestampStart(Timestamp value) {
		timestamp_start = value;
	}
	
	public void setTimestampFinish(Timestamp value) {
		timestamp_finish = value;
	}
	
	@Override
	public String toString() {
		String res = "order_status_id = " + (order_status_id == 0 ? "null" : order_status_id) + 
				", description = '" + description + "', car_id = " + 
				(car_id == 0 ? "null" : car_id) + ", timestamp_start = " + 
				(timestamp_start == null ? "current_timestamp()" : "PARSEDATETIME('" + 
				timestamp_start.toString() + "', 'yyyy-MM-dd HH:mm:ss.SS')") +
				", timestamp_finish = " + (timestamp_finish == null ? "null" : "PARSEDATETIME('" + 
				timestamp_finish.toString() + "', 'yyyy-MM-dd HH:mm:ss.SS')");
/*		String res = "order_status_id = " + (order_status_id == 0 ? "null" : order_status_id) + 
				", description = '" + description + "', car_id = " + 
				(car_id == 0 ? "null" : car_id);*/
		return res;
	}
	
	public OrderInWork(int order_status_id, String description, int car_id, Timestamp timestamp_start, Timestamp timestamp_finish) {
		this.order_status_id = order_status_id;
		this.description = description;
		this.car_id = car_id;
		this.timestamp_start = timestamp_start;
		this.timestamp_finish = timestamp_finish;
	}
	
	public OrderInWork() {
		this(0, "", 0, null, null);
	}
}
