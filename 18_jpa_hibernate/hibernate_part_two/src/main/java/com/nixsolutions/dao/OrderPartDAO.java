package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.OrderPart;

public interface OrderPartDAO extends GenericDAO<OrderPart> {
	
	public OrderPart getByPK(long orderId, int partId);
	
	public List<OrderPart> getByOrderId(long orderId);
}
