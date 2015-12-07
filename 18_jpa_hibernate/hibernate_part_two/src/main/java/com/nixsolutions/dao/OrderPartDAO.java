package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.Part;

public interface OrderPartDAO extends GenericDAO<OrderPart> {
	
	public OrderPart getByPK(long orderId, long partId);
	
	public List<OrderPart> getByOrderId(long orderId);
	
	public List<OrderPart> getOrderPartByPart(Part part);
}
