package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.dto.OrderInWorkCarStatus;
import com.nixsolutions.entities.OrderInWork;

public interface OrderInWorkService {

	List<OrderInWork> getAllOrderInWork();

	OrderInWork getOrderInWorkById(long id);

	void addOrderInWork(OrderInWork orderInWork);

	void updateOrderInWork(OrderInWork orderInWork);

	void deleteOrderInWork(OrderInWork orderInWork);

	OrderInWorkCarStatus getOrderInWorkCarStatusByOrderId(long orderId);

	List<OrderInWorkCarStatus> getAllOrderInWorkCarStatus();
}
