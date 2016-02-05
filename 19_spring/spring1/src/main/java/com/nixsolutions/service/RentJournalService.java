package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Client;
import com.nixsolutions.entity.RentJournal;

public interface RentJournalService {
	public List<RentJournal> getAllRents();
	public RentJournal getRentById(Long rentId);
	public List<RentJournal> getRentsByClientId(Long clientId);
	public void createRent(RentJournal rent);
	public void updateRent(RentJournal rent);
	public void deleteRent(RentJournal rent);
	public void returnedBooks(String[] returnedBooks);
	public void submitBooks(String[] selectedBooks, Client reader);
	public List<Book> checkBooksInList(String[] booksIds);
}
