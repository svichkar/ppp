package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Client;
import com.nixsolutions.entity.RentJournal;

public interface RentJournalService {
	 List<RentJournal> getAllRents();
	 RentJournal getRentById(Long rentId);
	 List<RentJournal> getRentsByClientId(Long clientId);
	 void createRent(RentJournal rent);
	 void updateRent(RentJournal rent);
	 void deleteRent(RentJournal rent);
	 void returnedBooks(String[] returnedBooks);
	 void submitBooks(String[] selectedBooks, Client reader);
	 List<Book> checkBooksInList(String[] booksIds);
}
