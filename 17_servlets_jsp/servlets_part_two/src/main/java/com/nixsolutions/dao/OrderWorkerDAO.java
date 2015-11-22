package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.OrderWorker;

public interface OrderWorkerDAO extends GenericDAO<OrderWorker> {
	
	public OrderWorker getByPK(int orderId, int partId);
	
	public List<OrderWorker> getByOrderId(int orderId);
}
