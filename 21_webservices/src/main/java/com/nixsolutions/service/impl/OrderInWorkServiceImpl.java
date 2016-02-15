package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.PartOrderDAO;
import com.nixsolutions.dto.OrderInWorkCarStatus;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.entities.PartOrder;
import com.nixsolutions.service.OrderInWorkService;

@Service
public class OrderInWorkServiceImpl implements OrderInWorkService {

	@Autowired
	private OrderInWorkDAO orderInWorkDaoImpl;

	@Autowired
	private OrderWorkerDAO orderWorkerDaoImpl;
	
	@Autowired
	private PartOrderDAO partOrderDaoImpl;

	@Override
	public List<OrderInWork> getAllOrderInWork() {
		return orderInWorkDaoImpl.getAll();
	}

	@Override
	public OrderInWork getOrderInWorkById(long id) {
		return orderInWorkDaoImpl.findByPK(id);
	}

	@Override
	public void addOrderInWork(OrderInWork orderInWork) {
		orderInWorkDaoImpl.create(orderInWork);
	}

	@Override
	public void updateOrderInWork(OrderInWork orderInWork) {
		orderInWorkDaoImpl.update(orderInWork);
	}

	@Override
	public void deleteOrderInWork(OrderInWork orderInWork) {
		for (OrderWorker orderWorker : orderWorkerDaoImpl.getAll()) {
			orderWorkerDaoImpl.delete(orderWorker);
		}
		for (PartOrder partOrder : partOrderDaoImpl.getAll())
		{
			partOrderDaoImpl.delete(partOrder);
		}
		orderInWorkDaoImpl.delete(orderInWork);
	}

	@Override
	public OrderInWorkCarStatus getOrderInWorkCarStatusByOrderId(long orderId) {
		OrderInWorkCarStatus result = null;
		OrderInWork orderInWork = orderInWorkDaoImpl.findByPK(orderId);
		if (orderInWork != null) {
			result = new OrderInWorkCarStatus();
			result.setCarId(orderInWork.getCar().getCarId());
			result.setId(orderInWork.getOrderInWorkId());
			result.setDatetimeEnd(orderInWork.getDatetimeEnd());
			result.setDatetimeStart(orderInWork.getDatetimeStart());
			result.setDescription(orderInWork.getDescription());
			result.setModel(orderInWork.getCar().getModel());
			result.setOrderStatusId(orderInWork.getOrderStatus().getOrderStatusId());
			result.setOrderStatusName(orderInWork.getOrderStatus().getOrderStatusName());
			result.setVin(orderInWork.getCar().getVin());
		}

		return result;
	}

	@Override
	public List<OrderInWorkCarStatus> getAllOrderInWorkCarStatus() {
		List<OrderInWorkCarStatus> result = new ArrayList<>();
		List<OrderInWork> orderInWorks = orderInWorkDaoImpl.getAll();
		for (OrderInWork orderInWork : orderInWorks) {
			OrderInWorkCarStatus orderInWorkCarStatus = new OrderInWorkCarStatus();
			orderInWorkCarStatus.setCarId(orderInWork.getCar().getCarId());
			orderInWorkCarStatus.setId(orderInWork.getOrderInWorkId());
			orderInWorkCarStatus.setDatetimeEnd(orderInWork.getDatetimeEnd());
			orderInWorkCarStatus.setDatetimeStart(orderInWork.getDatetimeStart());
			orderInWorkCarStatus.setDescription(orderInWork.getDescription());
			orderInWorkCarStatus.setModel(orderInWork.getCar().getModel());
			orderInWorkCarStatus.setOrderStatusId(orderInWork.getOrderStatus().getOrderStatusId());
			orderInWorkCarStatus.setOrderStatusName(orderInWork.getOrderStatus().getOrderStatusName());
			orderInWorkCarStatus.setVin(orderInWork.getCar().getVin());
			result.add(orderInWorkCarStatus);
		}

		return result;
	}

}
