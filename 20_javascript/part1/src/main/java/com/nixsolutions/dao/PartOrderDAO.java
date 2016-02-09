package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entities.PartOrder;

public interface PartOrderDAO extends GenericDao<PartOrder> {

	public List<PartOrder> getAllForOrder(long order_id);

	public PartOrder findbyPartAndOrder(long order_id, long part_id);
}
