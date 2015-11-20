package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entities.PersistenceException;

public interface GenericDAO<T> {
	
	public T create() throws PersistenceException;
	
	public T createFrom(T entity) throws PersistenceException;
	
	public void update(T entity) throws PersistenceException;
	
	public void delete(T entity) throws PersistenceException;
	
	public T getByPK(int id) throws PersistenceException;
	
	public List<T> getAll() throws PersistenceException;
}
