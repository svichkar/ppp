package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.dbObjects.Order_in_work;

public interface Order_in_workDao extends DBTables {

	public List<Order_in_work> getAllOrders();

	public Order_in_work getOrderInWorkByCar(String vin_number);

	public Order_in_work getOrderInWorkByCustomer(String last_name, String first_name);

	public Order_in_work getOrderByID(Integer order_id);

	public void createNewOrder(Car car, String description);

	public void setOrderStatus(String status, Order_in_work order_in_work);
}
