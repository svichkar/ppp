package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.OrderPart;

public interface OrderPartDAO extends GenericDAO<OrderPart> {
	
	public OrderPart getByPK(int orderId, int partId);
	
	public List<OrderPart> getByOrderId(int orderId);
}
