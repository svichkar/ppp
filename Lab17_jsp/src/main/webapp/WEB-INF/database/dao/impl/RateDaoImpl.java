package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.dao.RateDao;
import database.entity.Rate;
import database.util.ConnectionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RateDaoImpl implements RateDao {
	private final static Logger LOG = LogManager.getLogger(RateDaoImpl.class.getName());

	public RateDaoImpl() {
	}
	
	public void create(Character rateValue) {
		PreparedStatement stm = null;
		Connection conn = null;
		try {
            conn = ConnectionManager.getConnection();
			String sql = "INSERT INTO rate (rate_value) VALUES (?);";
			stm = conn.prepareStatement(sql);
			stm.setString(1, rateValue.toString());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
                    ConnectionManager.closeAllConnections();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	public void update(Rate rate) {
		PreparedStatement stm = null;
        Connection conn = null;
		String sql = "UPDATE rate SET rate_value=? WHERE rate_id=?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(2, rate.getId());
			stm.setString(1, rate.getRateValue().toString());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
                    ConnectionManager.closeAllConnections();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	public void delete(Rate rate) {
		PreparedStatement stm = null;
        Connection conn = null;
		String sql = "DELETE FROM rate WHERE rate_id=?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, rate.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
                    ConnectionManager.closeAllConnections();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	public Rate getByRateId(int rateId) {
		Rate rate = null;
        Connection conn = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM rate WHERE rate_id = ?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, rateId);
			ResultSet rs = stm.executeQuery();
			rs.next();
			rate = new Rate(rs.getInt("rate_id"), rs.getString("rate_value").charAt(0));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.closeAllConnections();
		}
		return rate;
	}

	public Rate getByRateValue(Character rateValue) {
		Rate rate = null;
        Connection conn = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM rate WHERE rate_value = ?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, rateValue.toString());
			ResultSet rs = stm.executeQuery();
			rs.next();
			rate = new Rate(rs.getInt("rate_id"), rs.getString("rate_value").charAt(0));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.closeAllConnections();
		}
		return rate;
	}

	public List<Rate> getAll() {
		List<Rate> toReturn = new ArrayList<Rate>();
		Statement stm = null;
        Connection conn = null;
		String sql = "SELECT * FROM rate;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add(new Rate(rs.getInt("rate_id"), rs.getString("rate_value").charAt(0)));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.closeAllConnections();
		}
		return toReturn;
	}

}
