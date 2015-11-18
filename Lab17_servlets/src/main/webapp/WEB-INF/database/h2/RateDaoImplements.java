package database.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.dao.RateDao;
import database.entities.Rate;

public class RateDaoImplements implements RateDao {
	private final static Logger LOG = LogManager.getLogger(RateDaoImplements.class.getName());
	private Connection conn;

	public RateDaoImplements(Connection connection) {
		conn = connection;
	}
	
	public void create(Character rateValue) {
		PreparedStatement stm = null;
		try {
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
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	public void update(Rate rate) {
		PreparedStatement stm = null;
		String sql = "UPDATE rate SET rate_value=? WHERE rate_id=?;";
		try {
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
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	public void delete(Rate rate) {
		PreparedStatement stm = null;
		String sql = "DELETE FROM rate WHERE rate_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, rate.getId());
			stm.executeUpdate();
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
		}
	}

	public Rate getByRateId(int rateId) {
		Rate rate = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM rate WHERE rate_id = ?;";
		try {
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
		}
		return rate;
	}

	public Rate getByRateValue(Character rateValue) {
		Rate rate = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM rate WHERE rate_value = ?;";
		try {
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
		}
		return rate;
	}

	public List<Rate> getAll() {
		List<Rate> toReturn = new ArrayList<Rate>();
		Statement stm = null;
		String sql = "SELECT * FROM rate;";
		try {
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
		}
		return toReturn;
	}

}
