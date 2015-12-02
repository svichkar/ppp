package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.JournalDao;
import entity.Journal;
import util.ConnectionManager;

public class JournalDaoImpl extends AbstractDaoImpl implements JournalDao {

	@Override
	public List<Journal> getAllJournalRecords() {
		return super.find(null, null);

	}

	@Override
	public List<Journal> addInfoToJournal(List<Journal> journalList) {
		try (Connection conn = ConnectionManager.getConnection()) {
			for (Journal journal : journalList) {
				try (Statement st = conn.createStatement()) {
					ResultSet rs = st.executeQuery(
							"SELECT b.name as BookName, b.author as BookAuthor,c.name as CATEGORY, bi.inventory_number as InventoryNumber, r.name as READERNAME, j.start_date as StartDate , j.end_date as EndDate FROM JOURNAL j JOIN bookinstance bi ON (j.book_instance_id=bi.id) JOIN book b ON (bi.book_id=b.id) JOIN category c ON (b.category_id=c.id) JOIN reader r ON (j.reader_id=r.id) WHERE j.id ="
									+ journal.getId());
					while (rs.next()) {
						String bookName = rs.getString("BOOKNAME");
						String bookAuthor = rs.getString("BOOKAUTHOR");
						String bookCategory = rs.getString("CATEGORY");
						int inventoryNumber = rs.getInt("INVENTORYNUMBER");
						String readerName = rs.getString("READERNAME");
						Date startDate = rs.getDate("STARTDATE");
						Date endDate = rs.getDate("ENDDATE");
						journal.setBookName(bookName);
						journal.setBookAuthor(bookAuthor);
						journal.setBookCategory(bookCategory);
						journal.setBookInventoryNumber(inventoryNumber);
						journal.setReaderName(readerName);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return journalList;
	}

	@Override
	public boolean deleteJournalRecordById(String id) {

		return super.delete("id=" + id);
	}

}
