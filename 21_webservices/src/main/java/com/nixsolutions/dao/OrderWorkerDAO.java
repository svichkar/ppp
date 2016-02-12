package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entities.OrderWorker;

public interface OrderWorkerDAO extends GenericDao<OrderWorker> {

	List<OrderWorker> getAllForOrder(long orderId);
	
	OrderWorker findbyOrderAndWorker(long orderId, long workerId);

}
