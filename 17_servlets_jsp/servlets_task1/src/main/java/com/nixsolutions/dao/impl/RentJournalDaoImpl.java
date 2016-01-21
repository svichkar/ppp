package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.H2ConnManager;
import com.nixsolutions.dao.RentJournalDao;
import com.nixsolutions.entity.RentJournal;


public class RentJournalDaoImpl implements RentJournalDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<RentJournal> getAllRents() {
		LOG.entry();
		String sql = "SELECT * FROM rent_journal;";
		Connection conn = null;
		Statement statem = null;
		ResultSet result = null;
		List<RentJournal> rentJournals = new ArrayList<>();
		try {
			conn = H2ConnManager.getConnection();
			statem = conn.createStatement();
			result = statem.executeQuery(sql);
			while (result.next()) {
				RentJournal rentJournal = new RentJournal();
				rentJournal.setRentId(result.getInt("ticket_id"));
				rentJournal.setBookId(result.getInt("book_id"));
				rentJournal.setClientId(result.getInt("client_id"));
				rentJournal.setRentDate(result.getDate("rent_date"));
				rentJournal.setReturnDate(result.getDate("return_date"));
				rentJournals.add(rentJournal);
			}
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all rentJournals", e));
		}finally {
			H2ConnManager.closeQuitely(result);
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
		return LOG.exit(rentJournals);
	}

	@Override
	public RentJournal getRentById(int rentId) {
		LOG.entry(rentId);
		String sql = "SELECT * FROM rent_journal WHERE ticket_id = ?;";
		RentJournal rentJournal = null;
		Connection conn = null;
		PreparedStatement statem = null;
		ResultSet result = null;
		try {conn = H2ConnManager.getConnection();
			statem = conn.prepareStatement(sql);
			statem.setInt(1, rentId);
			result = statem.executeQuery();
			if (result.next()) {
				rentJournal = new RentJournal();
				rentJournal.setRentId(result.getInt("ticket_id"));
				rentJournal.setBookId(result.getInt("book_id"));
				rentJournal.setClientId(result.getInt("client_id"));
				rentJournal.setRentDate(result.getDate("rent_date"));
				rentJournal.setReturnDate(result.getDate("return_date"));
			}
		} catch (SQLException e) {
			LOG.throwing(
					new DaoException("not able to get a rentJournal by Id", e));
		}finally {
			H2ConnManager.closeQuitely(result);
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
		return LOG.exit(rentJournal);
	}

	@Override
	public void createRent(RentJournal rentJournal) {
		LOG.entry(rentJournal.toString());
		String sql = "INSERT INTO rent_journal (book_id, client_id, rent_date, return_date) VALUES (?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement statem = null;
		try {
			conn = H2ConnManager.getConnection();
			statem = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			statem.setInt(1, rentJournal.getBookId());
			statem.setInt(2, rentJournal.getClientId());
			statem.setDate(3, (Date) rentJournal.getRentDate());
			statem.setDate(4, (Date) rentJournal.getReturnDate());
			statem.executeUpdate();
			conn.commit();
			LOG.exit("rentJournal was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create an author", e));
			H2ConnManager.rollbackQuitely(conn);
		} finally {
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
		}

	@Override
	public void updateRent(RentJournal rentJournal) {
		LOG.entry(rentJournal);
		LOG.debug(rentJournal.getRentDate().getTime());
		LOG.debug(rentJournal.getReturnDate().getTime());
		String sql = "UPDATE rent_journal SET book_id = ?, client_id = ?, rent_date = ?, return_date = ?  WHERE ticket_id = ?";
		Connection conn = null;
		PreparedStatement statem = null;
		try {
			conn = H2ConnManager.getConnection();
			statem = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			statem.setLong(1, rentJournal.getBookId());
			statem.setLong(2, rentJournal.getClientId());
			///
			Date sqlRentDate = new java.sql.Date(rentJournal.getRentDate().getTime());
			Date sqlReturnDate = new java.sql.Date(rentJournal.getReturnDate().getTime());
			statem.setDate(3, sqlRentDate);
			statem.setDate(4, sqlReturnDate);
			
			//statem.setDate(3, new java.sql.Date(rentJournal.getRentDate().getTime()));
			//statem.setDate(4, new java.sql.Date(rentJournal.getReturnDate().getTime()));
			//statem.setDate(3, (Date) rentJournal.getRentDate());
			//statem.setDate(4, (Date) rentJournal.getReturnDate());
			statem.setLong(5, rentJournal.getRentId());
			statem.executeUpdate();
			conn.commit();
			LOG.exit("rentJournal was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create an author", e));
			H2ConnManager.rollbackQuitely(conn);
		} finally {
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
	}

	@Override
	public void deleteRent(RentJournal rentJournal) {
		LOG.entry(rentJournal);
		String sql = "DELETE FROM rent_journal WHERE ticket_id = ?";
		Connection conn = null;
		PreparedStatement statem = null;
		try {
			conn = H2ConnManager.getConnection();
			statem = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			statem.setLong(1, rentJournal.getRentId());
			statem.executeUpdate();
			conn.commit();
			LOG.exit("rent was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create an author", e));
			H2ConnManager.rollbackQuitely(conn);
		} finally {
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
	}
}
