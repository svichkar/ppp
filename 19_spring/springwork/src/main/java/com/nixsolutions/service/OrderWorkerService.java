package com.nixsolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.hibernate.entity.Worker;

@Service
public class OrderWorkerService {

	@Autowired
	private OrderWorkerDAO orderWorkerDao;

	public List<OrderWorker> getAllOrderWorkersByOrderId(long orderId) {
		return orderWorkerDao.getByOrderId(orderId);
	}

	public List<OrderWorker> getAllOrderWorkersByWorker(Worker worker) {
		return orderWorkerDao.getOrderWorkerByWorker(worker);
	}

	public void deleteOrderWorker(OrderWorker orderWorker) {
		orderWorkerDao.delete(orderWorker);
	}
}
