package com.nixsolutions.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	//public T create();
	
	public T createFrom(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public T getByPK(int id);
	
	public List<T> getAll();
}
