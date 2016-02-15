package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.dto.AllPartsInOrder;
import com.nixsolutions.entities.PartOrder;

public interface PartOrderService {

	PartOrder getPartOrderbyPartAndOrder(long orderId, long partId);

	List<PartOrder> getAllPartOrderByOrderId(long orderId);

	List<PartOrder> getAllPartOrder();

	void addPartOrder(PartOrder partOrder);

	void updatePartOrder(PartOrder partOrder);

	void deletePartOrder(PartOrder partOrder);

	List<AllPartsInOrder> getAllPartOrders(long orderId);

	AllPartsInOrder findByPartAndOrder(long orderId, long partId);

}
