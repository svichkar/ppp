package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.bean.OrderWorkerBean;
import com.nixsolutions.hibernate.entity.OrderWorker;

public interface OrderWorkerService {

	List<OrderWorker> getOrderWorkersByOrderId(long orderId);

	List<OrderWorkerBean> getOrderWorkersAsBeansByOrderId(long orderId);

	List<OrderWorker> getOrderWorkersByWorkerId(int workerId);

	OrderWorker getOrderWorkerByIds(long orderId, int workerId);

	OrderWorkerBean getOrderWorkerByIdsAsBean(long orderId, int workerId);

	void addOrderWorker(OrderWorker orderWorker);

	void updateOrderWorker(OrderWorker orderWorker);

	void deleteOrderWorker(OrderWorker orderWorker);
}
