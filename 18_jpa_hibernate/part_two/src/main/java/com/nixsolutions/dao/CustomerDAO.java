package com.nixsolutions.dao;

public interface CustomerDAO<T> extends GenericDao<T> {

	public T findByFullName(String f_name, String l_name);

}
