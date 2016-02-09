package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.BookDao;
import com.nixsolutions.dao.RentJournalDao;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Client;
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.service.BookService;
import com.nixsolutions.service.RentJournalService;

@Service("rentJournalService")
@Transactional
public class RentJournalServiceImpl implements RentJournalService{

	@Autowired
	private RentJournalDao rentJournalDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<RentJournal> getAllRents() {
		return rentJournalDao.getAllRents();
	}

	@Override
	public RentJournal getRentById(Long rentId) {
		return rentJournalDao.getRentById(rentId);
	}

	@Override
	public List<RentJournal> getRentsByClientId(Long clientId) {
		return rentJournalDao.getRentsByClientId(clientId);
	}

	@Override
	public void createRent(RentJournal rent) {
		rentJournalDao.createRent(rent);
		
	}

	@Override
	public void updateRent(RentJournal rent) {
		rentJournalDao.updateRent(rent);
	}

	@Override
	public void deleteRent(RentJournal rent) {
		rentJournalDao.deleteRent(rent);
	}
	
	public void returnedBooks(String[] returnedBooks){
		
		for (String rentId : returnedBooks) {
			RentJournal rent = rentJournalDao.getRentById(Long.valueOf(rentId));
			Book returnedBook = rent.getBook();
			rent.setReturnDate(new java.sql.Date(new Date().getTime()));
			returnedBook.increaseCount();
			rent.setBook(returnedBook);
			rentJournalDao.updateRent(rent);
		}
	}
	
	public void submitBooks(String[] selectedBooks, Client reader){
		for (String id : selectedBooks) {
			RentJournal rent = new RentJournal();
			Book loanedBook = bookDao.getBookById(Long.valueOf(id));
			rent.setBook(loanedBook);
			rent.setClient(reader);
			rent.setRentDate(new Date());
			if (loanedBook.getCount() > 0) {
				loanedBook.decreaseCount();
				bookDao.updateBook(loanedBook);
				rentJournalDao.createRent(rent);
			}
		}
	}
	
	public List<Book> checkBooksInList(String[] booksIds) {
		List<Book> toBeloaned = new ArrayList<>();
		if (booksIds != null) {
			for (String bookId : booksIds) {
				Book book = bookDao.getBookById(Long.valueOf(bookId));
				if (book.getCount() > 0) {
					toBeloaned.add(book);
				}
			}
		}
		return toBeloaned;
	}

}
