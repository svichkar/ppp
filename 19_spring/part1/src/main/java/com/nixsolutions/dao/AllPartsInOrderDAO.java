package com.nixsolutions.dao;

import java.util.List;

public interface AllPartsInOrderDAO<T> extends GenericDao<T> {

	public List<T> getAll(long orderid);
	
	public T findByPartAndOrder(long orderid, long partid);
	
}
