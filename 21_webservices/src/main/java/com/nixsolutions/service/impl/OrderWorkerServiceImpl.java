package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dto.AllWorkersInOrder;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.service.OrderWorkerService;

@Service
public class OrderWorkerServiceImpl implements OrderWorkerService {

	@Autowired
	private OrderWorkerDAO orderWorkerDaoImpl;

	@Override
	public List<OrderWorker> getAllOrderWorker() {
		return orderWorkerDaoImpl.getAll();
	}

	@Override
	public List<OrderWorker> getAllOrderWorker(long orderId) {
		return orderWorkerDaoImpl.getAllForOrder(orderId);
	}

	@Override
	public OrderWorker getOrderWorkerByOrderAndWorker(long orderid, long workerid) {
		return orderWorkerDaoImpl.findbyOrderAndWorker(orderid, workerid);
	}

	@Override
	public List<AllWorkersInOrder> getAllWorkersInOrderById(long orderid) {
		List<AllWorkersInOrder> result = new ArrayList<>();
		List<OrderWorker> orderWorkers = orderWorkerDaoImpl.getAllForOrder(orderid);
		for (OrderWorker orderWorker : orderWorkers) {
			AllWorkersInOrder allWorkerInOrder = new AllWorkersInOrder();
			allWorkerInOrder.setCompleted(orderWorker.getCompleted());
			allWorkerInOrder.setFname(orderWorker.getWorker().getFname());
			allWorkerInOrder.setLname(orderWorker.getWorker().getLname());
			allWorkerInOrder.setId(orderWorker.getOrderInWork().getOrderInWorkId());
			allWorkerInOrder.setWorkerId(orderWorker.getWorker().getWorkerId());
			result.add(allWorkerInOrder);
		}
		return result;
	}

	@Override
	public AllWorkersInOrder getAllWorkersInOrderByOrderAndWorker(long orderId, long worderId) {
		AllWorkersInOrder result = null;
		OrderWorker orderWorker = orderWorkerDaoImpl.findbyOrderAndWorker(orderId, worderId);
		if (orderWorker != null) {
			result = new AllWorkersInOrder();
			result.setCompleted(orderWorker.getCompleted());
			result.setFname(orderWorker.getWorker().getFname());
			result.setLname(orderWorker.getWorker().getLname());
			result.setId(orderWorker.getOrderInWork().getOrderInWorkId());
			result.setWorkerId(orderWorker.getWorker().getWorkerId());
		}
		return result;
	}

	@Override
	public void addOrderWorker(OrderWorker orderWorker) {
		orderWorkerDaoImpl.create(orderWorker);
	}

	@Override
	public void updateOrderWorker(OrderWorker orderWorker) {
		orderWorkerDaoImpl.update(orderWorker);
	}

	@Override
	public void deleteOrderWorker(OrderWorker orderWorker) {
		orderWorkerDaoImpl.delete(orderWorker);
	}

}
