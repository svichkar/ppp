package com.nixsolutions.dao;

import com.nixsolutions.entity.OrderPart;

public interface OrderPartDAO extends GenericDAO<OrderPart> {
	
	public OrderPart getByPK(int orderId, int partId);
}
