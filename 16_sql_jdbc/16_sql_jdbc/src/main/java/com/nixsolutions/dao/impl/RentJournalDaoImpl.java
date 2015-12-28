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

import com.nixsolutions.app.H2ConnManager;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.RentJournalDao;
import com.nixsolutions.entity.RentJournal;

public class RentJournalDaoImpl implements RentJournalDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<RentJournal> getAllRents() {
		LOG.entry();
		String sql = "SELECT * FROM rent_journal;";
		List<RentJournal> rentJournals = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection();
				Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
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
			LOG.error("not able to get rentJournals", e);
			LOG.throwing(new DaoException("not able to get all rentJournals"));
		}
		return LOG.exit(rentJournals);
	}

	@Override
	public RentJournal getRentById(int rentId) {
		LOG.entry(rentId);
		String sql = "SELECT * FROM rent_journal WHERE ticket_id = ?;";
		RentJournal rentJournal = null;
		try (Connection conn = H2ConnManager.getConnection();
				PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, rentId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				rentJournal = new RentJournal();
				rentJournal.setRentId(result.getInt("ticket_id"));
				rentJournal.setBookId(result.getInt("book_id"));
				rentJournal.setClientId(result.getInt("client_id"));
				rentJournal.setRentDate(result.getDate("rent_date"));
				rentJournal.setReturnDate(result.getDate("return_date"));
			}
		} catch (SQLException e) {
			LOG.error("not able to get a rentJournal by Id", e);
			LOG.throwing(
					new DaoException("not able to get a rentJournal by Id"));
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
			LOG.error("not able to create an author", e);
			LOG.throwing(new DaoException("not able to create an author"));
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (statem != null) {
				try {
					statem.close();
				} catch (SQLException e1) {// no need to trace it
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {// no need to trace it
				}
			}
		}
	}

	@Override
	public void updateRent(RentJournal rentJournal) {
		LOG.entry(rentJournal);
		String sql = "UPDATE rent_journal SET book_id = ?, client_id = ?, rent_date = ?, return_date = ?  WHERE ticket_id = ?";
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
			statem.setInt(5, rentJournal.getRentId());
			statem.executeUpdate();
			conn.commit();
			LOG.exit("rentJournal was updated");
		} catch (SQLException e) {
			LOG.error("not able to update the author", e);
			LOG.throwing(new DaoException("not able to update the author"));
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (statem != null) {
				try {
					statem.close();
				} catch (SQLException e1) {// no need to trace it
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {// no need to trace it
				}
			}
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
			LOG.error("not able to delete the author", e);
			LOG.throwing(new DaoException("not able to delete the author"));
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (statem != null) {
				try {
					statem.close();
				} catch (SQLException e1) {// no need to trace it
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {// no need to trace it
				}
			}
		}
	}
}
