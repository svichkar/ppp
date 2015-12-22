/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderStatusDao;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.service.OrderStatusService;

/**
 * @author mixeyes
 *
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService {

	@Autowired
	private OrderStatusDao statusDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderStatusService#createNewStatus(java.lang.
	 * String)
	 */
	@Override
	public void createNewStatus(String status) {
		statusDao.createNewStatus(status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.OrderStatusService#getAllStatus()
	 */
	@Override
	public List<OrderStatus> getAllStatus() {
		return statusDao.getAllStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.OrderStatusService#deleteStatusByID(java.lang.
	 * Integer)
	 */
	@Override
	public boolean deleteStatusByID(Integer order_status_id) {
		return statusDao.deleteStatusByID(order_status_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.OrderStatusService#getStatusByID(java.lang.
	 * Integer)
	 */
	@Override
	public OrderStatus getStatusByID(Integer status_id) {
		return statusDao.getStatusByID(status_id);
	}

}
