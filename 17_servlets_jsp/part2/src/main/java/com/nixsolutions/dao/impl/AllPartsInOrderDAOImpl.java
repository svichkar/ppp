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
import com.nixsolutions.dao.AllPartsInOrderDAO;
import com.nixsolutions.entities.AllPartsInOrder;

public class AllPartsInOrderDAOImpl implements AllPartsInOrderDAO<AllPartsInOrder> {

	private final static Logger LOG = LogManager.getLogger(AllPartsInOrderDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;
	private List<AllPartsInOrder> listResults;

	public AllPartsInOrderDAOImpl() {

	}

	@Deprecated
	@Override
	public boolean create(AllPartsInOrder t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean update(AllPartsInOrder t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean delete(AllPartsInOrder t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public AllPartsInOrder findByPK(int id) {
		return null;
	}

	@Override
	public List<AllPartsInOrder> getAll() {
		boolean resultExecution;
		listResults = new ArrayList<>();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();

			ps = conn.prepareStatement("SELECT po.*, p.part_name, p.vendor FROM part p JOIN part_order po ON p.part_id=po.part_id");
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					AllPartsInOrder allParts = new AllPartsInOrder();
					allParts.setId(rs.getInt("order_id"));
					allParts.setPart_id(rs.getInt("part_id"));
					allParts.setPart_name(rs.getString("part_name"));
					allParts.setVendor(rs.getString("vendor"));
					allParts.setAmount(rs.getInt("amount"));
					listResults.add(allParts);
				}
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
		return listResults;
	}

	public List<AllPartsInOrder> getAll(int order_id) {
		boolean resultExecution;
		listResults = new ArrayList<>();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();

			ps = conn.prepareStatement(
					"SELECT po.*, p.part_name, p.vendor FROM part p JOIN part_order po ON p.part_id=po.part_id WHERE po.order_id=?");
			ps.setInt(1, order_id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					AllPartsInOrder allParts = new AllPartsInOrder();
					allParts.setId(rs.getInt("order_id"));
					allParts.setPart_id(rs.getInt("part_id"));
					allParts.setPart_name(rs.getString("part_name"));
					allParts.setVendor(rs.getString("vendor"));
					allParts.setAmount(rs.getInt("amount"));
					listResults.add(allParts);
				}
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
		return listResults;
	}

	public AllPartsInOrder findByPartAndOrder(int order_id, int part_id) {
		boolean resultExecution;
		AllPartsInOrder allpart = new AllPartsInOrder();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();

			ps = conn.prepareStatement(
					"SELECT po.*, p.part_name, p.vendor FROM part p JOIN part_order po ON p.part_id=po.part_id WHERE po.order_id=?");
			ps.setInt(1, order_id);
			ps.setInt(2, part_id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					allpart.setId(rs.getInt("order_id"));
					allpart.setPart_id(rs.getInt("part_id"));
					allpart.setPart_name(rs.getString("part_name"));
					allpart.setVendor(rs.getString("vendor"));
					allpart.setAmount(rs.getInt("amount"));
				}
			} else {
				allpart = null;
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
		return allpart;
	}
}
