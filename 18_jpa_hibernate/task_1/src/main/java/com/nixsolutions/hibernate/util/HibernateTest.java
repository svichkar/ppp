package com.nixsolutions.hibernate.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nixsolutions.hibernate.entity.Author;
import com.nixsolutions.hibernate.entity.Book;
import com.nixsolutions.hibernate.entity.Category;
import com.nixsolutions.hibernate.entity.Cell;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;

public class HibernateTest {
	private static final Logger LOG = LogManager.getLogger();
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {			
			transaction = session.beginTransaction();
			
			Cell cell = new Cell("test");
			Cell cell1 = new Cell("test1");
			Cell cell2 = new Cell("test2");
			Cell cell3 = new Cell("test3");
			
			Category category = new Category("Horror");
			
			Book book = new Book();
			book.setCategory(category);
			book.setCell(cell1);
			book.setName("new book");
			
			Author auth = new Author();
			auth.setFirstName("Steven");
			auth.setSecondName("King");
			
			Role role = new Role("user");
			
			User user = new User("Ann", "123fgasga", role);
			
			session.save(cell);
			session.save(cell1);
			session.save(cell2);
			session.save(cell3);
			session.save(role);
			session.save(user);
			session.save(auth);
			session.save(category);
			session.save(book);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.error(e);
		}finally {
			session.close();
		}
		
	}

}
