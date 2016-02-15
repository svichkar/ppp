package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entities.PartOrder;

public interface PartOrderDAO extends GenericDao<PartOrder> {

	List<PartOrder> getAllForOrder(long orderId);

	PartOrder findbyPartAndOrder(long orderId, long partId);
}
