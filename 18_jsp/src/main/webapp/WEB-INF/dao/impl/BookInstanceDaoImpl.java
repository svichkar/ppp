package dao.impl;

import dao.BookInstanceDao;

public class BookInstanceDaoImpl extends AbstractDaoImpl implements BookInstanceDao{

	@Override
	public boolean deleteByBookID(String bookId) {
		
		return super.delete("book_id=" + bookId);
	}


}
