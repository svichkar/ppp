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
import com.nixsolutions.dao.PartOrderDAO;
import com.nixsolutions.entities.PartOrder;

public class PartOrderDAOImpl implements PartOrderDAO<PartOrder> {

	private final static Logger LOG = LogManager
			.getLogger(PartOrderDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public PartOrderDAOImpl() {
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		} catch (ClassNotFoundException ex) {
			LOG.error(ex, ex);
		}
	}

	@Override
	public boolean create(PartOrder t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("INSERT INTO part_order (order_id, part_id, amount) VALUES (?,?,?);");
			ps.setInt(1, t.getId());
			ps.setInt(2, t.getPartid());
			ps.setInt(3, t.getAmount());
			executionResult = ps.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(PartOrder t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("UPDATE part_order SET amount=?  WHERE order_id=? AND part_id=?");
			ps.setInt(1, t.getAmount());
			ps.setInt(2, t.getId());
			ps.setInt(3, t.getPartid());
			executionResult = ps.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(PartOrder t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("DELETE FROM part_order WHERE order_id=? AND part_id=?");
			ps.setInt(1, t.getId());
			ps.setInt(2, t.getPartid());
			executionResult = ps.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public PartOrder findByPK(int id) {
		return null;
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
	public List<PartOrder> getAll() {
		boolean resultExecution;
		List<PartOrder> lPO = new ArrayList<PartOrder>();
		try {
			ps = conn.prepareStatement("SELECT * FROM part_order");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					PartOrder po = new PartOrder();
					po.setId(rs.getInt("part_id"));
					po.setId(rs.getInt("order_id"));
					po.setAmount(rs.getInt("amount"));
					lPO.add(po);
				}
			} else {
				lPO = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return lPO;
	}

}
