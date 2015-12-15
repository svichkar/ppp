package com.nixsolutions.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	void createFrom(T entity);
	
	void update(T entity);
	
	void delete(T entity);
	
	T getByPK(int id);
	
	List<T> getAll();
}
