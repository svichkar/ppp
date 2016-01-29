package com.nixsolutions.dao;

import java.util.List;

public interface OrderWorkerDAO<T> extends GenericDao<T> {

	public List<T> getAllForOrder(long orderid);
	
	public T findbyOrderAndWorker(long orderid, long workerid);

}
