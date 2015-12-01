package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.OrderWorker;

public interface OrderWorkerDAO extends GenericDAO<OrderWorker> {
	
	public OrderWorker getByPK(int orderId, int workerId);
	
	public List<OrderWorker> getByOrderId(long orderId);
}
