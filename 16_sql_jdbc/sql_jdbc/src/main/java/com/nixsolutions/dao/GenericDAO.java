package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	
	public T create() throws SQLException;
	
	public T createFrom(T entity) throws SQLException;
	
	public void update(T entity) throws SQLException;
	
	public void delete(T entity) throws SQLException;
	
	public T getByPK(int id) throws SQLException;
	
	public List<T> getAll() throws SQLException;
}
