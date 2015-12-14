package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.bean.OrderWorkerBean;
import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.hibernate.entity.Worker;

public interface OrderWorkerService {

	List<OrderWorker> getOrderWorkersByOrderId(long orderId);

	List<OrderWorkerBean> getOrderWorkersAsBeansByOrderId(long orderId);

	List<OrderWorker> getOrderWorkersByWorker(Worker worker);

	OrderWorker getOrderWorkerByIds(long orderId, int workerId);

	OrderWorkerBean getOrderWorkerByIdsAsBean(long orderId, int workerId);

	void addOrderWorker(OrderWorker orderWorker);

	void updateOrderWorker(OrderWorker orderWorker);

	void deleteOrderWorker(OrderWorker orderWorker);
}
