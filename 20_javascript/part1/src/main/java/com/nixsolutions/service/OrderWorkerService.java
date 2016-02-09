package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.AllWorkersInOrder;
import com.nixsolutions.entities.OrderWorker;;

public interface OrderWorkerService {

	public List<OrderWorker> getAllOrderWorker();
	
	public List<OrderWorker> getAllOrderWorker(long orderId);

	public OrderWorker getOrderWorkerByOrderAndWorker(long orderid, long workerid);
	
	public List<AllWorkersInOrder> getAllWorkersInOrderById(long orderid);
	
	public AllWorkersInOrder getAllWorkersInOrderByOrderAndWorker(long orderId, long worderId);

	public void addOrderWorker(OrderWorker orderWorker);

	public void updateOrderWorker(OrderWorker orderWorker);

	public void deleteOrderWorker(OrderWorker orderWorker);

}
