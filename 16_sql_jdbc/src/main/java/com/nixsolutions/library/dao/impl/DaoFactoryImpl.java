package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.dao.*;

/**
 * Created by kozlovskij on 12/24/2015.
 */
public class DaoFactoryImpl implements DaoFactory {
    @Override
    public AuthorBookDAO getAuthorBookDAO() {
        return new AuthorBookDaoImpl();
    }

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
}
