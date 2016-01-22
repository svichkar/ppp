package com.nixsolutions.dao;

import com.nixsolutions.dao.impl.AuthorDaoImpl;
import com.nixsolutions.dao.impl.BookDaoImpl;
import com.nixsolutions.dao.impl.CategoryDaoImpl;
import com.nixsolutions.dao.impl.CellDaoImpl;
import com.nixsolutions.dao.impl.ClientDaoImpl;
import com.nixsolutions.dao.impl.RentJournalDaoImpl;
import com.nixsolutions.dao.impl.RoleDaoImpl;
import com.nixsolutions.dao.impl.UserDaoImpl;


public class H2DaoFactory extends DaoFactory{

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

	@Override
	public UserDao getUserDao() {
		return new UserDaoImpl();
	}

	@Override
	public RoleDao getRoleDao() {
		return new RoleDaoImpl();
	}
}
