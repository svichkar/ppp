package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.bean.OrderWorkerBean;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.hibernate.entity.Worker;
import com.nixsolutions.service.OrderWorkerService;

@Service
public class OrderWorkerServiceImpl implements OrderWorkerService {

	@Autowired
	private OrderWorkerDAO orderWorkerDao;

	public List<OrderWorker> getOrderWorkersByOrderId(long orderId) {
		return orderWorkerDao.getByOrderId(orderId);
	}

	public List<OrderWorkerBean> getOrderWorkersAsBeansByOrderId(long orderId) {
		return processAsBeans(getOrderWorkersByOrderId(orderId));
	}

	public List<OrderWorker> getOrderWorkersByWorker(Worker worker) {
		return orderWorkerDao.getOrderWorkerByWorker(worker);
	}

	public OrderWorker getOrderWorkerByIds(long orderId, int workerId) {
		return orderWorkerDao.getByPK(orderId, workerId);
	}

	@SuppressWarnings("serial")
	public OrderWorkerBean getOrderWorkerByIdsAsBean(long orderId, int workerId) {
		return processAsBeans(new ArrayList<OrderWorker>() {
			{
				add(getOrderWorkerByIds(orderId, workerId));
			}
		}).get(0);
	}

	public void addOrderWorker(OrderWorker orderWorker) {
		orderWorkerDao.createFrom(orderWorker);
	}

	public void updateOrderWorker(OrderWorker orderWorker) {
		orderWorkerDao.update(orderWorker);
	}

	public void deleteOrderWorker(OrderWorker orderWorker) {
		orderWorkerDao.delete(orderWorker);
	}

	private List<OrderWorkerBean> processAsBeans(List<OrderWorker> orderWorkerList) {
		List<OrderWorkerBean> resultList = new ArrayList<>();
		for (OrderWorker item : orderWorkerList) {
			OrderWorkerBean owb = new OrderWorkerBean();
			owb.setOrderId(item.getOrder().getOrderId());
			Worker w = item.getWorker();
			owb.setWorkerId(w.getWorkerId());
			owb.setWorkerName(w.getFirstName() + " " + w.getLastName());
			owb.setIsCompleted(item.getIsCompleted().toString());
			resultList.add(owb);
		}
		return resultList;
	}
}
