/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.dao.OrderWorkerDao;
import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.dao.WorkerStatusDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.service.OrderWorkerService;

/**
 * @author mixeyes
 *
 */
@Service
public class OrderWorkerServiceImpl implements OrderWorkerService {

	@Autowired
	private OrderWorkerDao orderWorkerDao;
	@Autowired
	private OrderInWorkDao orderDao;
	@Autowired
	private WorkerDao workerDao;
	@Autowired
	private WorkerStatusDao workerStatusDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderWorkerService#assignWorkerToOrder(java.lang
	 * .Integer, java.lang.Integer)
	 */
	@Override
	public void assignWorkerToOrder(Integer orderId, Integer workerId) {
		OrderInWork orderInWork = orderDao.getOrderByID(orderId);
		Worker worker = workerDao.getWorkerByID(workerId);
		worker.setWorkerStatus(workerStatusDao.getWorkerStatus("busy"));
		workerDao.updateWorker(worker);
		OrderWorker orderWorker = orderWorkerDao.getOrderWorker(orderId, workerId);
		if (orderWorker == null) {
			orderWorker = new OrderWorker();
			orderWorker.setOrder(orderInWork);
			orderWorker.setWorker(worker);
			orderWorker.setCompleted(false);
			orderWorkerDao.saveOrUpdateOrderWorker(orderWorker);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderWorkerService#assignWorkerToOrder(java.lang
	 * .Integer, java.lang.Integer)
	 */
	@Override
	public void assignWorkerToOrder(String orderId, String workerId) {
		assignWorkerToOrder(Integer.decode(orderId), Integer.decode(workerId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.OrderWorkerService#changeStatus(java.lang.
	 * Integer, java.lang.Integer, boolean)
	 */
	@Override
	public void changeStatus(Integer orderId, Integer workerId, boolean isCompleted) {
		OrderWorker orderWorker = orderWorkerDao.getOrderWorker(orderId, workerId);
		orderWorker.setCompleted(isCompleted);
		orderWorkerDao.saveOrUpdateOrderWorker(orderWorker);
		if (isCompleted) {
			Worker worker = workerDao.getWorkerByID(workerId);
			worker.setWorkerStatus(workerStatusDao.getWorkerStatus("free"));
			workerDao.updateWorker(worker);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.OrderWorkerService#changeStatus(java.lang.
	 * Integer, java.lang.Integer, boolean)
	 */
	@Override
	public void changeStatus(String orderId, String workerId, boolean isCompleted) {
		changeStatus(Integer.decode(orderId), Integer.decode(workerId), isCompleted);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.OrderWorkerService#getAll()
	 */
	@Override
	public List<OrderWorker> getAll() {
		return orderWorkerDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderWorkerService#getActiveOrderByWorkerID(java
	 * .lang.Integer)
	 */
	@Override
	public OrderInWork getActiveOrderByWorkerID(Integer workerId) {
		return orderWorkerDao.getActiveOrderByWorkerID(workerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderWorkerService#getWorkersByOrderID(java.lang
	 * .Integer)
	 */
	@Override
	public List<OrderWorker> getWorkersByOrderID(Integer orderId) {
		return orderWorkerDao.getWorkersByOrderID(orderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderWorkerService#getWorkersByOrderID(java.lang
	 * .Integer)
	 */
	@Override
	public List<OrderWorker> getWorkersByOrderID(String orderId) {
		return orderWorkerDao.getWorkersByOrderID(Integer.decode(orderId));
	}

}
