package com.nixsolutions.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.nixsolutions.app.H2ConnManager;
import com.nixsolutions.dao.impl.AuthorBookDaoImpl;
import com.nixsolutions.dao.impl.AuthorDaoImpl;
import com.nixsolutions.dao.impl.BookDaoImpl;
import com.nixsolutions.dao.impl.CategoryDaoImpl;
import com.nixsolutions.dao.impl.CellDaoImpl;
import com.nixsolutions.dao.impl.ClientDaoImpl;
import com.nixsolutions.dao.impl.RentJournalDaoImpl;

public class DaoFactory {
	
	public Connection getConnection() throws SQLException {
		Connection connect = H2ConnManager.getConnection();
		return connect;
	}

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
