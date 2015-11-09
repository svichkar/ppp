package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.dbObjects.Order_in_work;

public interface Order_in_workDao extends DBTables {

	public List<Order_in_work> getAllOrders();

	public Order_in_work getOrderByCar();

	public Order_in_work getOrderByCustomer();

	public Order_in_work getOrderByID();

	public void createNewOrder(Car car, String description);

	public void setOrderStatus(String status, Order_in_work order_in_work);
}
