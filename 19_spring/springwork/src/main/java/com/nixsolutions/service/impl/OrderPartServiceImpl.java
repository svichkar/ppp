package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.bean.OrderPartBean;
import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.service.OrderPartService;

@Service
public class OrderPartServiceImpl implements OrderPartService {

	@Autowired
	private OrderPartDAO orderPartDao;

	public List<OrderPart> getOrderPartsByOrderId(long orderId) {
		return orderPartDao.getByOrderId(orderId);
	}

	public List<OrderPartBean> getOrderPartsAsBeansByOrderId(long orderId) {
		return processAsBeans(getOrderPartsByOrderId(orderId));
	}

	public List<OrderPart> getOrderPartsByPart(Part part) {
		return orderPartDao.getOrderPartByPart(part);
	}

	public OrderPart getOrderPartByIds(long orderId, long partId) {
		return orderPartDao.getByPK(orderId, partId);
	}

	@SuppressWarnings("serial")
	public OrderPartBean getOrderPartByIdsAsBean(long orderId, long partId) {
		return processAsBeans(new ArrayList<OrderPart>() {
			{
				add(getOrderPartByIds(orderId, partId));
			}
		}).get(0);
	}

	public void addOrderPart(OrderPart orderPart) {
		orderPartDao.createFrom(orderPart);
	}

	public void updateOrderPart(OrderPart orderPart) {
		orderPartDao.update(orderPart);
	}

	public void deleteOrderPart(OrderPart orderPart) {
		orderPartDao.delete(orderPart);
	}

	private List<OrderPartBean> processAsBeans(List<OrderPart> orderPartList) {
		List<OrderPartBean> resultList = new ArrayList<>();
		for (OrderPart item : orderPartList) {
			OrderPartBean opb = new OrderPartBean();
			opb.setOrderId(item.getOrder().getOrderId());
			Part p = item.getPart();
			opb.setPartId(p.getPartId());
			opb.setPartName(p.getPartName());
			opb.setUsedAmount(item.getUsedAmount());
			resultList.add(opb);
		}
		return resultList;
	}
}
