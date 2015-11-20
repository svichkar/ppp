package com.nixsolutions.dao;

import com.nixsolutions.entities.PersistenceException;

public interface DAOFactory<Context> {
	
	public interface DAOCreator<Context> {
        public GenericDAO<?> create(Context context);
    }
	
    public Context getContext() throws PersistenceException;

    public GenericDAO<?> getDao(Context context, Class<?> dtoClass) throws PersistenceException;
}
