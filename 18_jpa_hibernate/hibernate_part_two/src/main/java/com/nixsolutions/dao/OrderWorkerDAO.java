package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.hibernate.entity.Worker;

public interface OrderWorkerDAO extends GenericDAO<OrderWorker> {
	
	public OrderWorker getByPK(long orderId, int workerId);
	
	public List<OrderWorker> getByOrderId(long orderId);
	
	public List<OrderWorker> getOrderWorkerByWorker(Worker worker);
}
