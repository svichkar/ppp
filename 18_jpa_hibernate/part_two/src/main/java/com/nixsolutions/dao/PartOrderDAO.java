package com.nixsolutions.dao;

import java.util.List;

public interface PartOrderDAO<T> extends GenericDao<T> {

	public List<T> getAllForOrder(long order_id);

	public T findbyPartAndOrder(long order_id, long part_id);
}
