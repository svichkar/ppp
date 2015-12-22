package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.nixsolutions.app.H2ConnManager;
import com.nixsolutions.dao.AuthorBookDao;
import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.dao.BookDao;
import com.nixsolutions.dao.CategoryDao;
import com.nixsolutions.dao.CellDao;
import com.nixsolutions.dao.ClientDao;
import com.nixsolutions.dao.RentJournalDao;

public class DaoFactory {
	
	public Connection getConnection() throws SQLException {
		Connection connect = H2ConnManager.getConnection();
		return connect;
	}

	public AuthorBookDao getAuthorBookDao() {
		return null;
	}

	public AuthorDao getAuthorDao() {
		return new AuthorDaoImpl();
	}

	public BookDao getBookDao() {
		return null;
	}

	public CategoryDao getCategoryDao() {
		return null;
	}

	public CellDao getCellDao() {
		return null;
	}

	public ClientDao getClientDao() {
		return null;
	}

	public RentJournalDao getRentJournalDao() {
		return null;
	}
}
