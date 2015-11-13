package com.jdbcDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.nixsolutions.DBCreationThroughDrivermanager;
import com.nixsolutions.DropAllTablesFromDB;

public class WorkWithDb {

	static final Logger LOG = LogManager.getLogger(Book.class);
	public static void main(String[] args) {

		DropAllTablesFromDB.main(null);
		DBCreationThroughDrivermanager.main(null);

		// Create one book category
		Category cat = new Category();
		try {
			cat.create(Arrays.asList("name"), Arrays.asList("Development"));
		} catch (SQLException e) {
			LOG.error(e, e);

		}

		// Fill Book table with some data
		Book book = new Book();
		try {
			for (int i = 0; i < 100; i++) {
				book.create(
						Arrays.asList("name", "author", "publisher",
								"category_id"),
						Arrays.asList(generateString(), generateString(),
								generateString(), "1"));
			}
		} catch (SQLException e1) {
			LOG.error(e1, e1);
		}

		// Fill Reader table with some random data
		Reader r = new Reader();
		try {
			for (int i = 0; i < 100; i++) {
				r.create(Arrays.asList("name", "adress"),
						Arrays.asList(generateString(), generateString()));
			}
		} catch (SQLException e) {
			LOG.error(e, e);
		}

		// Fill BookInstance table with some random data
		BookInstance bi = new BookInstance();
		ResultSet bookIds = book.find(new String[]{"id"}, null);

		try {
			while (bookIds.next()) {
				Integer temp = bookIds.getInt("id");
				Integer num = new Random().nextInt(99);
				bi.create(Arrays.asList("book_id", "inventory_number"),
						Arrays.asList(temp.toString(), num.toString()));
			}
		} catch (SQLException e) {
			LOG.error(e, e);
		}

		// Fill Journal Table
		Journal j = new Journal();
		try {
			List<BookInstance> biList = bi.findElement(new String[]{"id"},
					null);
			List<Reader> rList = r.findElement(new String[]{"id"}, null);
			if (biList.size() != rList.size())
				throw new SQLException("Size missmatch");
			for (int i = 0; i < rList.size(); i++) {

				j.create(Arrays.asList("book_id", "reader_id", "start_date",
						"end_date"),
						Arrays.asList(
								new Integer(biList.get(i).getId()).toString(),
								new Integer(rList.get(i).getId()).toString(),
								"2010-01-01", "2013-10-17"));
			}

		} catch (SQLException e) {

			LOG.error(e, e);
		}

	}

	// Strings generator
	public static String generateString() {
		Random rng = new Random();
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int length = 15;
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}

}
