package com.nixsolutions.library.dao;


/**
 * Created by kozlovskij on 12/24/2015.
 */
public interface DaoFactory {
    public AuthorBookDAO getAuthorBookDAO();

    public AuthorDAO getAuthorDAO();

    public BookDAO getBookDAO();

    public CategoryDAO getCategoryDAO();

    public CellDAO getCellDAO();

    public ClientDAO getClientDAO();

    public TicketDAO getTicketDAO();

    public RoleDAO getRoleDAO();

    public UserDAO getUserDAO();

}
