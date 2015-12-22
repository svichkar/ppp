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
	public void assignWorkerToOrder(Integer order_id, Integer worker_id) {
		OrderInWork orderInWork = orderDao.getOrderByID(order_id);
		Worker worker = workerDao.getWorkerByID(worker_id);
		worker.setWorker_status(workerStatusDao.getWorkerStatus("busy"));
		workerDao.updateWorker(worker);
		OrderWorker orderWorker = orderWorkerDao.getOrderWorker(order_id, worker_id);
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
	public void assignWorkerToOrder(String order_id, String worker_id) {
		assignWorkerToOrder(Integer.decode(order_id), Integer.decode(worker_id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.OrderWorkerService#changeStatus(java.lang.
	 * Integer, java.lang.Integer, boolean)
	 */
	@Override
	public void changeStatus(Integer order_id, Integer worker_id, boolean isCompleted) {
		OrderWorker orderWorker = orderWorkerDao.getOrderWorker(order_id, worker_id);
		orderWorker.setCompleted(isCompleted);
		orderWorkerDao.saveOrUpdateOrderWorker(orderWorker);
		if (isCompleted) {
			Worker worker = workerDao.getWorkerByID(worker_id);
			worker.setWorker_status(workerStatusDao.getWorkerStatus("free"));
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
	public void changeStatus(String order_id, String worker_id, boolean isCompleted) {
		changeStatus(Integer.decode(order_id), Integer.decode(worker_id), isCompleted);
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
	public OrderInWork getActiveOrderByWorkerID(Integer worker_id) {
		return orderWorkerDao.getActiveOrderByWorkerID(worker_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderWorkerService#getWorkersByOrderID(java.lang
	 * .Integer)
	 */
	@Override
	public List<OrderWorker> getWorkersByOrderID(Integer order_id) {
		return orderWorkerDao.getWorkersByOrderID(order_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderWorkerService#getWorkersByOrderID(java.lang
	 * .Integer)
	 */
	@Override
	public List<OrderWorker> getWorkersByOrderID(String order_id) {
		return orderWorkerDao.getWorkersByOrderID(Integer.decode(order_id));
	}

}
