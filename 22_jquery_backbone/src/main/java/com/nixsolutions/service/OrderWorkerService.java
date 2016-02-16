package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.dto.AllWorkersInOrder;
import com.nixsolutions.entities.OrderWorker;;

public interface OrderWorkerService {

	List<OrderWorker> getAllOrderWorker();

	List<OrderWorker> getAllOrderWorker(long orderId);

	OrderWorker getOrderWorkerByOrderAndWorker(long orderid, long workerid);

	List<AllWorkersInOrder> getAllWorkersInOrderById(long orderid);

	AllWorkersInOrder getAllWorkersInOrderByOrderAndWorker(long orderId, long worderId);

	void addOrderWorker(OrderWorker orderWorker);

	void updateOrderWorker(OrderWorker orderWorker);

	void deleteOrderWorker(OrderWorker orderWorker);

}
