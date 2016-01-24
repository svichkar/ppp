package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.app.ConnectionManager;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.entities.Part;

public class PartDAOImpl implements PartDAO<Part> {

	private final static Logger LOG = LogManager.getLogger(PartDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public PartDAOImpl() {
		
	}

	@Override
	public boolean create(Part t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn
					.prepareStatement("INSERT INTO part (part_name, vendor, amount) VALUES (?,?,?)");
			ps.setString(1, t.getPart_name());
			ps.setString(2, t.getVendor());
			ps.setInt(3, t.getAmount());
			executionResult = ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Part t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn
					.prepareStatement("UPDATE part SET part_name=?, vendor=?, amount=?  WHERE part_id=?");
			ps.setString(1, t.getPart_name());
			ps.setString(2, t.getVendor());
			ps.setInt(3, t.getAmount());
			ps.setInt(4, t.getId());
			executionResult = ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Part t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("DELETE FROM part WHERE part_id=?");
			ps.setInt(1, t.getId());
			executionResult = ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Part findByPK(int id) {
		boolean resultExecution;
		Part part = new Part();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM part WHERE part_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					part.setId(rs.getInt("part_id"));
					part.setPart_name(rs.getString("part_name"));
					part.setVendor(rs.getString("vendor"));
					part.setAmount(rs.getInt("amount"));
				}
			} else {
				part = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		return part;
	}

	public void Dispose() {

		if (this.ps != null) {
			try {
				this.ps.close();
			} catch (SQLException ex) {
				LOG.error(ex, ex);
			}
		}

		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException ex) {
				LOG.error(ex, ex);
			}
		}

	}

	@Override
	public List<Part> getAll() {
		boolean resultExecution;
		List<Part> lPart = new ArrayList<Part>();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM part");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					Part part = new Part();
					part.setId(rs.getInt("part_id"));
					part.setPart_name(rs.getString("part_name"));
					part.setVendor(rs.getString("vendor"));
					part.setAmount(rs.getInt("amount"));
					lPart.add(part);
				}
			} else {
				lPart = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}

		return lPart;
	}

}
