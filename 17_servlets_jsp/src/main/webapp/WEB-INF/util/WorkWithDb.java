package util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import app.DBCreationThroughDrivermanager;
import app.DropAllTablesFromDB;
import dao.BookFactory;
import dao.BookInstanceFactory;
import dao.CategoryFactory;
import dao.CommonFactory;
import dao.JournalFactory;
import dao.ReaderFactory;
import entity.Book;
import entity.BookInstance;
import entity.Reader;

public class WorkWithDb {

	static final Logger LOG = LogManager.getLogger(Book.class);
	public static void main(String[] args) {

		 //DropAllTablesFromDB.main(null);
		// DBCreationThroughDrivermanager.main(null);

		// Create one book category
		CategoryFactory instance = new CategoryFactory();

		instance.addNewRow(Arrays.asList("name"), Arrays.asList("Development"));

		// Fill Book table with some data
		BookFactory book = new BookFactory();

		for (int i = 0; i < 100; i++) {
			book.addNewRow(
					Arrays.asList("name", "author", "publisher", "category_id"),
					Arrays.asList(generateString(), generateString(),
							generateString(), "1"));
		}

		// Fill Reader table with some random data
		ReaderFactory r = new ReaderFactory();

		for (int i = 0; i < 100; i++) {
			r.addNewRow(Arrays.asList("name", "adress"),
					Arrays.asList(generateString(), generateString()));
		}

		// Fill BookInstance table with some random data
		BookInstanceFactory bi = new BookInstanceFactory();
		List<Book> bookIds = book.find(new String[]{"id"}, null);

		for (Book booky : bookIds) {
			Integer temp = booky.getId();
			Integer num = new Random().nextInt(99);
			bi.addNewRow(Arrays.asList("book_id", "inventory_number"),
					Arrays.asList(temp.toString(), num.toString()));
		}

		// Fill Journal Table

		List<BookInstance> biList = bi.find(new String[]{"id"}, null);
		List<Reader> rList = r.find(new String[]{"id"}, null);
		if (biList.size() != rList.size())
			LOG.error("Different arrays ");
		CommonFactory j = new JournalFactory();
		for (int i = 0; i < rList.size(); i++) {

			j.addNewRow(
					Arrays.asList("book_instance_id", "reader_id", "start_date",
							"end_date"),
					Arrays.asList(new Integer(biList.get(i).getId()).toString(),
							new Integer(rList.get(i).getId()).toString(),
							"2010-01-01", "2013-10-17"));
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
