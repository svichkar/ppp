package com.nixsolutions.dao;

public interface DaoFactory<Context> {

	public interface CreateDAO {
		GenericDao<?> create();
	}

	public GenericDao<?> getDao(Class<?> doClass) throws Exception;

}
