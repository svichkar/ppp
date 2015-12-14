package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.bean.OrderPartBean;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.Part;

public interface OrderPartService {

	List<OrderPart> getOrderPartsByOrderId(long orderId);

	List<OrderPartBean> getOrderPartsAsBeansByOrderId(long orderId);

	List<OrderPart> getOrderPartsByPart(Part part);

	OrderPart getOrderPartByIds(long orderId, long partId);

	OrderPartBean getOrderPartByIdsAsBean(long orderId, long partId);

	void addOrderPart(OrderPart orderPart);

	void updateOrderPart(OrderPart orderPart);

	void deleteOrderPart(OrderPart orderPart);
}
