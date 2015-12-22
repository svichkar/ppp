/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.dao.PartDao;
import com.nixsolutions.dao.PartOrderDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.PartOrder;
import com.nixsolutions.service.PartOrderService;

/**
 * @author mixeyes
 *
 */
@Service
public class PartOrderServiceImpl implements PartOrderService {

	@Autowired
	private PartOrderDao partOrderDao;
	@Autowired
	private OrderInWorkDao orderDao;
	@Autowired
	private PartDao partDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartOrderService#getAllParts()
	 */
	@Override
	public List<PartOrder> getAllParts() {
		return partOrderDao.getAllParts();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.PartOrderService#getPartsByOrderId(java.lang.
	 * Integer)
	 */
	@Override
	public List<PartOrder> getPartsByOrderId(Integer order_id) {
		return partOrderDao.getPartsByOrderId(order_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.PartOrderService#getPartsByOrderId(java.lang.
	 * Integer)
	 */
	@Override
	public List<PartOrder> getPartsByOrderId(String order_id) {
		return partOrderDao.getPartsByOrderId(Integer.decode(order_id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartOrderService#setPartToOrder(java.lang.
	 * Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void setPartToOrder(Integer order_id, Integer part_id, Integer amount) {
		PartOrder partOrder = new PartOrder();
		OrderInWork orderInWork = orderDao.getOrderByID(order_id);
		Part part = partDao.getPart(part_id);
		partOrder.setAmount(amount);
		partOrder.setOrder(orderInWork);
		partOrder.setPart(part);
		partOrderDao.saveOrUpdatePartOrder(partOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartOrderService#setPartToOrder(java.lang.
	 * Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void setPartToOrder(String order_id, String part_id, String amount) {
		Integer orderId = Integer.decode(order_id);
		Integer partId = Integer.decode(part_id);
		Integer count = Integer.decode(amount);
		PartOrder partOrder = partOrderDao.getPartByOrderId(orderId, partId);
		if (partOrder != null) {
			partOrder.setAmount(partOrder.getAmount() + count);
		} else {
			partOrder = new PartOrder();
			OrderInWork orderInWork = orderDao.getOrderByID(orderId);
			Part part = partDao.getPart(partId);
			partOrder.setAmount(count);
			partOrder.setOrder(orderInWork);
			partOrder.setPart(part);
		}
		partOrderDao.saveOrUpdatePartOrder(partOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.PartOrderService#deletePartFromOrder(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public void deletePartFromOrder(String order_id, String part_id) {
		PartOrder partOrder = partOrderDao.getPartByOrderId(Integer.decode(order_id), Integer.decode(part_id));
		partOrderDao.deletePartFromOrder(partOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartOrderService#updatePartOrder(java.lang.
	 * Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updatePartOrder(Integer order_id, Integer part_id, Integer amount) {
		PartOrder partOrder = getPartByOrderId(order_id, part_id);
		partOrder.setAmount(amount);

		partOrderDao.saveOrUpdatePartOrder(partOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.PartOrderService#getPartByOrderId(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public PartOrder getPartByOrderId(Integer order_id, Integer part_id) {
		return partOrderDao.getPartByOrderId(order_id, part_id);
	}

}
