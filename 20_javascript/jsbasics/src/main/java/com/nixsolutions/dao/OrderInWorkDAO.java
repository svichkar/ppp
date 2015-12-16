package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.OrderInWork;

public interface OrderInWorkDAO extends GenericDAO<OrderInWork> {

	List<OrderInWork> getOrdersByUserId(int userId);

	List<OrderInWork> getOrdersByCarId(int carId);

	List<OrderInWork> getOrdersByCustomerId(int customerId);

	OrderInWork getByPK(long id);
}
