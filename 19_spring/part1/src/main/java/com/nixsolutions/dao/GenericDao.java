package com.nixsolutions.dao;

import java.util.List;

public interface GenericDao<T> {

	void create(T t) throws Exception;

	void update(T t) throws Exception;

	void delete(T t) throws Exception;

	T findByPK(long id);

	List<T> getAll();

}
