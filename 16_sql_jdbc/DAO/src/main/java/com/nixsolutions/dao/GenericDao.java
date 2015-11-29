package com.nixsolutions.dao;

import java.util.List;

public interface GenericDao<T> {

	boolean create(T t);

	boolean update(T t);

	boolean delete(T t);

	T findByPK(int id);

	List<T> getAll();

}
