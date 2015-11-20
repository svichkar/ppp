package com.nixsolutions.dao;

import java.sql.SQLException;

import com.nixsolutions.entity.OrderPart;

public interface OrderPartDAO extends GenericDAO<OrderPart> {
	
	public OrderPart getByPK(int orderId, int partId) throws SQLException;
}
