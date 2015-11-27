package dao.impl;

import dao.BookDao;
import dao.BookInstanceDao;
import dao.CategoryDao;
import dao.DaoFactory;
import dao.JournalDao;
import dao.ReaderDao;
import dao.UsersDao;

public class DBDaoFactory implements DaoFactory{

	@Override
	public BookDao getBookDao() {
		// TODO Auto-generated method stub
		return new BookDaoImpl();
	}

	@Override
	public BookInstanceDao getBookInstanceDao() {
		// TODO Auto-generated method stub
		return new BookInstanceDaoImpl();
	}

	@Override
	public CategoryDao getCategoryDao() {
		// TODO Auto-generated method stub
		return new CategoryDaoImpl();
	}

	@Override
	public JournalDao getJournalDao() {
		// TODO Auto-generated method stub
		return new JournalDaoImpl();
	}

	@Override
	public ReaderDao getReaderDao() {
		// TODO Auto-generated method stub
		return new ReaderDaoImpl();
	}

	@Override
	public UsersDao getUsersDao() {
		// TODO Auto-generated method stub
		return new UsersDaoImpl();
	}

}
