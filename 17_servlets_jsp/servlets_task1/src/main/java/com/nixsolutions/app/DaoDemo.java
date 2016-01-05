package com.nixsolutions.app;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.AuthorBook;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Category;
import com.nixsolutions.entity.Cell;
import com.nixsolutions.entity.Client;
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

public class DaoDemo {
	
	public static void main(String[] args) {
		H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

		// creation of the author table entries
		Author authMiha = new Author();
		authMiha.setFirstName("Michail");
		authMiha.setSecondName("Lelyakov");
		Author authKing = new Author();
		authKing.setFirstName("Steven");
		authKing.setSecondName("King");
		Author authBulgak = new Author();
		authBulgak.setFirstName("Michail");
		authBulgak.setSecondName("Bulgakov");
		
		factory.getAuthorDao().createAuthor(authMiha);
		factory.getAuthorDao().createAuthor(authKing);
		factory.getAuthorDao().createAuthor(authBulgak);

		// creation of the cell table entries
		Cell cellA = new Cell("cell A");
		Cell cellB = new Cell("cell B");
		Cell cellC = new Cell("cell C");

		factory.getCellDao().createCell(cellA);
		factory.getCellDao().createCell(cellB);
		factory.getCellDao().createCell(cellC);

		// creation of the category table entries
		Category drama = new Category("Drama");
		Category action = new Category("Action");
		Category horror = new Category("Horror");

		factory.getCategoryDao().createCategory(drama);
		factory.getCategoryDao().createCategory(action);
		factory.getCategoryDao().createCategory(horror);

		// creation of the client table entries
		Client client1 = new Client();
		client1.setFirstName("Vitaliy");
		client1.setSecondName("Rybkin");
		client1.setEmail("vitaliy@email.com");
		client1.setPhone("123-23434-532");

		Client client2 = new Client();
		client2.setFirstName("Alexandra");
		client2.setSecondName("Adusheva");
		client2.setEmail("alexandra@email.com");
		client2.setPhone("123-23434-532");

		Client client3 = new Client();
		client3.setFirstName("Evgeniy");
		client3.setSecondName("Fomin");
		client3.setEmail("evgeniy@email.com");
		client3.setPhone("123-23434-532");

		factory.getClientDao().createClient(client1);
		factory.getClientDao().createClient(client2);
		factory.getClientDao().createClient(client3);

		// creation of the book table entry
		Book book1 = new Book();
		book1.setName("book1");
		book1.setCategoryId(1);
		book1.setCellId(1);

		Book book2 = new Book();
		book2.setName("book2");
		book2.setCategoryId(2);
		book2.setCellId(2);

		Book book3 = new Book();
		book3.setName("book3");
		book3.setCategoryId(2);
		book3.setCellId(2);

		factory.getBookDao().createBook(book1);
		factory.getBookDao().createBook(book2);
		factory.getBookDao().createBook(book3);

		// creation of the author_book table entry
		AuthorBook authBook1 = new AuthorBook(1, 1);
		AuthorBook authBook2 = new AuthorBook(2, 2);
		AuthorBook authBook3 = new AuthorBook(3, 3);

		factory.getAuthorBookDao().createAuthorBook(authBook1);
		factory.getAuthorBookDao().createAuthorBook(authBook2);
		factory.getAuthorBookDao().createAuthorBook(authBook3);

		// creation of the rent_journal entry
		RentJournal rentJournal1 = new RentJournal();
		rentJournal1.setBookId(1);
		rentJournal1.setClientId(1);
		rentJournal1.setRentDate(Date.valueOf("2015-08-21"));
		rentJournal1.setReturnDate(null);

		RentJournal rentJournal2 = new RentJournal();
		rentJournal2.setBookId(2);
		rentJournal2.setClientId(1);
		rentJournal2.setRentDate(Date.valueOf("2015-08-21"));
		rentJournal2.setReturnDate(null);

		RentJournal rentJournal3 = new RentJournal();
		rentJournal3.setBookId(3);
		rentJournal3.setClientId(3);
		rentJournal3.setRentDate(Date.valueOf("2015-08-21"));
		rentJournal3.setReturnDate(Date.valueOf("2015-08-22"));

		factory.getRentJournalDao().createRent(rentJournal1);
		factory.getRentJournalDao().createRent(rentJournal2);
		factory.getRentJournalDao().createRent(rentJournal3);

		// get a client and update of the client's information
		Client client = factory.getClientDao().getClientById(1);
		System.out.println("name before update: " + client.getFirstName()
				+ "; lastname before update: " + client.getSecondName()
				+ "; client email before update: " + client.getEmail());

		client.setFirstName("Oleg");
		client.setSecondName("Orehov");
		client.setEmail("oleg@mail.com");
		
		factory.getClientDao().updateClient(client);
		client = factory.getClientDao().getClientById(1);
		System.out.println("name after update: " + client.getFirstName()
		+ "; lastname after update: " + client.getSecondName()
		+ "; client email after update: " + client.getEmail());

		// get all the cells from cell table and delete a cell from cell table
		List<Cell> cells = new ArrayList<>();
		cells = factory.getCellDao().getAllCells();
		System.out.println("cells count before delete: " + cells.size());
		factory.getCellDao().deleteCell(cells.get(2));
		cells = factory.getCellDao().getAllCells();
		System.out.println("cells count after delete: " + cells.size());
		
		//create roles
		Role role1 = new Role("admin");
		Role role2 = new Role("regular");
		factory.getRoleDao().createRole(role1);
		factory.getRoleDao().createRole(role2);
		
		//create users
		User user1 = new User("Jax", "Pswd", 1);
		User user2 = new User("Shmax", "Pswd", 2);
		factory.getUserDao().createUser(user1);
		factory.getUserDao().createUser(user2);
		
	}

}
