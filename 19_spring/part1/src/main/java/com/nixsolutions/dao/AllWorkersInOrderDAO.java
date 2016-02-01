package com.nixsolutions.dao;

import java.util.List;

public interface AllWorkersInOrderDAO<T> extends GenericDao<T> {

	public List<T> getAll(long orderid);

	public T findByOrderAndWorker(long orderid, long worderid);

}
