package com.nixsolutions.dao;

import com.nixsolutions.entity.OrderWorker;

public interface OrderWorkerDAO extends GenericDAO<OrderWorker> {
	
	public OrderWorker getByPK(int orderId, int partId);
}
