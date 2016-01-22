package com.nixsolutions.dao;

import com.nixsolutions.hibernate.util.HibernateUtil;

public abstract class DaoFactory {
	public static final int H2 = 1;
	public static final int HIBER = 2;
	public static final int ORACLE = 3;
	public static final int SYBASE = 4;

	public abstract AuthorDao getAuthorDao();

	public abstract BookDao getBookDao();

	public abstract CategoryDao getCategoryDao();

	public abstract CellDao getCellDao();

	public abstract ClientDao getClientDao();

	public abstract RentJournalDao getRentJournalDao();

	public abstract UserDao getUserDao();

	public abstract RoleDao getRoleDao();

	public static H2DaoFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case H2:
			return new H2DaoFactory();
		case HIBER:
			return null; //new HibernateUtil(); 
		case ORACLE:
			return null; // not implemented
		case SYBASE:
			return null; // not implemented
		default:
			return null;
		}
	}
}
