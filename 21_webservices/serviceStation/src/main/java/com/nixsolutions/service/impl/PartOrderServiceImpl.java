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
	public List<PartOrder> getPartsByOrderId(Integer orderId) {
		return partOrderDao.getPartsByOrderId(orderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.PartOrderService#getPartsByOrderId(java.lang.
	 * Integer)
	 */
	@Override
	public List<PartOrder> getPartsByOrderId(String orderId) {
		return partOrderDao.getPartsByOrderId(Integer.decode(orderId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartOrderService#setPartToOrder(java.lang.
	 * Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void setPartToOrder(Integer orderId, Integer partId, Integer amount) {
		PartOrder partOrder = new PartOrder();
		OrderInWork orderInWork = orderDao.getOrderByID(orderId);
		Part part = partDao.getPart(partId);
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
	public void setPartToOrder(String orderId, String partId, String amount) {
		Integer ordId = Integer.decode(orderId);
		Integer parId = Integer.decode(partId);
		Integer count = Integer.decode(amount);
		PartOrder partOrder = partOrderDao.getPartByOrderId(ordId, parId);
		if (partOrder != null) {
			partOrder.setAmount(partOrder.getAmount() + count);
		} else {
			partOrder = new PartOrder();
			OrderInWork orderInWork = orderDao.getOrderByID(ordId);
			Part part = partDao.getPart(parId);
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
	public void deletePartFromOrder(String orderId, String partId) {
		PartOrder partOrder = partOrderDao.getPartByOrderId(Integer.decode(orderId), Integer.decode(partId));
		partOrderDao.deletePartFromOrder(partOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartOrderService#updatePartOrder(java.lang.
	 * Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updatePartOrder(Integer orderId, Integer partId, Integer amount) {
		PartOrder partOrder = getPartByOrderId(orderId, partId);
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
	public PartOrder getPartByOrderId(Integer orderId, Integer partId) {
		return partOrderDao.getPartByOrderId(orderId, partId);
	}

}
