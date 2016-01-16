package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.*;


/**
 * Created by kozlovskij on 12/24/2015.
 */
public class DaoFactoryImpl implements DaoFactory {

    @Override
    public AuthorDAO getAuthorDAO() {
        return new AuthorDaoImpl();
    }

    @Override
    public BookDAO getBookDAO() {
        return new BookDaoImpl();
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new CategoryDaoImpl();
    }

    @Override
    public CellDAO getCellDAO() {
        return new CellDaoImpl();
    }

    @Override
    public ClientDAO getClientDAO() {
        return new ClientDaoImpl();
    }

    @Override
    public TicketDAO getTicketDAO() {
        return new TicketDaoImpl();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new RoleDaoImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDaoImpl();
    }

}
