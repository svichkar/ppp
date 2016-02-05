package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderInWorkCarStatus;

public interface OrderInWorkService {

	public List<OrderInWork> getAllOrderInWork();

	public OrderInWork getOrderInWorkById(long id);

	public void addOrderInWork(OrderInWork orderInWork);

	public void updateOrderInWork(OrderInWork orderInWork);

	public void deleteOrderInWork(OrderInWork orderInWork);

	public OrderInWorkCarStatus getOrderInWorkCarStatusByOrderId(long orderId);
	
	public List<OrderInWorkCarStatus> getAllOrderInWorkCarStatus();
}
