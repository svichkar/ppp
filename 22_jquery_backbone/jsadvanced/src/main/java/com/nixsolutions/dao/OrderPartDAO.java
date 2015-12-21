package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.OrderPart;

public interface OrderPartDAO extends GenericDAO<OrderPart> {

	OrderPart getByPK(long orderId, long partId);

	List<OrderPart> getByOrderId(long orderId);

	List<OrderPart> getOrderPartByPartId(long partId);
}
