package com.nixsolutions.dao;

import com.nixsolutions.dao.impl.AuthorBookDaoImpl;
import com.nixsolutions.dao.impl.AuthorDaoImpl;
import com.nixsolutions.dao.impl.BookDaoImpl;
import com.nixsolutions.dao.impl.CategoryDaoImpl;
import com.nixsolutions.dao.impl.CellDaoImpl;
import com.nixsolutions.dao.impl.ClientDaoImpl;
import com.nixsolutions.dao.impl.RentJournalDaoImpl;

public class H2DaoFactory extends DaoFactory{

	public AuthorBookDao getAuthorBookDao() {
		return new AuthorBookDaoImpl();
	}

	public AuthorDao getAuthorDao() {
		return new AuthorDaoImpl();
	}

	public BookDao getBookDao() {
		return new BookDaoImpl();
	}

	public CategoryDao getCategoryDao() {
		return new CategoryDaoImpl();
	}

	public CellDao getCellDao() {
		return new CellDaoImpl();
	}

	public ClientDao getClientDao() {
		return new ClientDaoImpl();
	}

	public RentJournalDao getRentJournalDao() {
		return new RentJournalDaoImpl();
	}
}
