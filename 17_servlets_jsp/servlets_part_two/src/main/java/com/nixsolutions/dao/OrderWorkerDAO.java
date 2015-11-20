package com.nixsolutions.dao;

import java.sql.SQLException;

import com.nixsolutions.entity.OrderWorker;

public interface OrderWorkerDAO extends GenericDAO<OrderWorker> {
	
	public OrderWorker getByPK(int orderId, int partId) throws SQLException;
}
