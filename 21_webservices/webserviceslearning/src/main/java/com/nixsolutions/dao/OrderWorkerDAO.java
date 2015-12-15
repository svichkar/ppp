package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.OrderWorker;

public interface OrderWorkerDAO extends GenericDAO<OrderWorker> {

	OrderWorker getByPK(long orderId, int workerId);

	List<OrderWorker> getByOrderId(long orderId);

	List<OrderWorker> getOrderWorkerByWorkerId(int workerId);
}
