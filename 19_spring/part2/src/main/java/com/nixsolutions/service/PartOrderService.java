package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.AllPartsInOrder;
import com.nixsolutions.entities.PartOrder;

public interface PartOrderService {

	public PartOrder getPartOrderbyPartAndOrder(long orderId, long partId);

	public List<PartOrder> getAllPartOrderByOrderId(long orderId);

	public List<PartOrder> getAllPartOrder();

	public void addPartOrder(PartOrder partOrder);

	public void updatePartOrder(PartOrder partOrder);

	public void deletePartOrder(PartOrder partOrder);
	
	public List<AllPartsInOrder> getAllPartOrders(long orderid);
	
	public AllPartsInOrder findByPartAndOrder(long orderid, long partid);

}
