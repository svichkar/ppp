package com.nixsolutions.dao;

import java.util.List;

public interface GenericDao<T> {

	void create(T t);

	void update(T t);

	void delete(T t);

	T findByPK(long id);

	List<T> getAll();

}
