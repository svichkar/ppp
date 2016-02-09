package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderInWorkCarStatus;
import com.nixsolutions.service.OrderInWorkService;

@Service
public class OrderInWorkServiceImpl implements OrderInWorkService {

	@Autowired
	private OrderInWorkDAO orderInWorkDaoImpl;

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
		orderInWorkDaoImpl.delete(orderInWork);
	}

	@Override
	public OrderInWorkCarStatus getOrderInWorkCarStatusByOrderId(long orderId) {
		OrderInWorkCarStatus result = null;
		OrderInWork orderInWork = orderInWorkDaoImpl.findByPK(orderId);
		if (orderInWork != null) {
			result = new OrderInWorkCarStatus();
			result.setCar_id(orderInWork.getCar().getCarId());
			result.setId(orderInWork.getOrderInWorkId());
			result.setDatetime_end(orderInWork.getDatetime_end());
			result.setDatetime_start(orderInWork.getDatetime_start());
			result.setDescription(orderInWork.getDescription());
			result.setModel(orderInWork.getCar().getModel());
			result.setOrder_status_id(orderInWork.getOrder_status().getOrderStatusId());
			result.setOrder_status_name(orderInWork.getOrder_status().getOrder_status_name());
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
			orderInWorkCarStatus.setCar_id(orderInWork.getCar().getCarId());
			orderInWorkCarStatus.setId(orderInWork.getOrderInWorkId());
			orderInWorkCarStatus.setDatetime_end(orderInWork.getDatetime_end());
			orderInWorkCarStatus.setDatetime_start(orderInWork.getDatetime_start());
			orderInWorkCarStatus.setDescription(orderInWork.getDescription());
			orderInWorkCarStatus.setModel(orderInWork.getCar().getModel());
			orderInWorkCarStatus.setOrder_status_id(orderInWork.getOrder_status().getOrderStatusId());
			orderInWorkCarStatus.setOrder_status_name(orderInWork.getOrder_status().getOrder_status_name());
			orderInWorkCarStatus.setVin(orderInWork.getCar().getVin());
			result.add(orderInWorkCarStatus);
		}

		return result;
	}

}
