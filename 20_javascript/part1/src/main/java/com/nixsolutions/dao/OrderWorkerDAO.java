package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entities.OrderWorker;

public interface OrderWorkerDAO extends GenericDao<OrderWorker> {

	public List<OrderWorker> getAllForOrder(long orderid);
	
	public OrderWorker findbyOrderAndWorker(long orderid, long workerid);

}
