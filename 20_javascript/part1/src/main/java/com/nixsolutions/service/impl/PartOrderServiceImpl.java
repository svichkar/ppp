package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.PartOrderDAO;
import com.nixsolutions.entities.AllPartsInOrder;
import com.nixsolutions.entities.PartOrder;
import com.nixsolutions.service.PartOrderService;

@Service
public class PartOrderServiceImpl implements PartOrderService {

	@Autowired
	private PartOrderDAO partOrderDaoImpl;

	@Override
	public PartOrder getPartOrderbyPartAndOrder(long orderId, long partId) {
		return partOrderDaoImpl.findbyPartAndOrder(orderId, partId);
	}

	@Override
	public List<PartOrder> getAllPartOrderByOrderId(long orderId) {
		return partOrderDaoImpl.getAllForOrder(orderId);
	}

	@Override
	public List<PartOrder> getAllPartOrder() {
		return partOrderDaoImpl.getAll();
	}

	@Override
	public void addPartOrder(PartOrder partOrder) {
		partOrderDaoImpl.create(partOrder);
	}

	@Override
	public void updatePartOrder(PartOrder partOrder) {
		partOrderDaoImpl.update(partOrder);
	}

	@Override
	public void deletePartOrder(PartOrder partOrder) {
		partOrderDaoImpl.delete(partOrder);
	}

	@Override
	public List<AllPartsInOrder> getAllPartOrders(long orderid) {
		List<AllPartsInOrder> result = new ArrayList<>();
		List<PartOrder> partOrders = partOrderDaoImpl.getAllForOrder(orderid);
		for (PartOrder partOrder : partOrders) {
			AllPartsInOrder allPartsInOrder = new AllPartsInOrder();
			allPartsInOrder.setAmount(partOrder.getAmount());
			allPartsInOrder.setId(orderid);
			allPartsInOrder.setPart_id(partOrder.getPart().getPartId());
			allPartsInOrder.setPart_name(partOrder.getPart().getPart_name());
			allPartsInOrder.setVendor(partOrder.getPart().getVendor());
			result.add(allPartsInOrder);
		}
		return result;
	}

	@Override
	public AllPartsInOrder findByPartAndOrder(long orderid, long partid) {
		AllPartsInOrder result = null;
		PartOrder partOrder = partOrderDaoImpl.findbyPartAndOrder(orderid, partid);
		if (partOrder != null) {
			result = new AllPartsInOrder();
			result.setAmount(partOrder.getAmount());
			result.setId(partOrder.getOrder().getOrderInWorkId());
			result.setPart_id(partOrder.getPart().getPartId());
			result.setPart_name(partOrder.getPart().getPart_name());
			result.setVendor(partOrder.getPart().getVendor());
		}
		return result;
	}

}
