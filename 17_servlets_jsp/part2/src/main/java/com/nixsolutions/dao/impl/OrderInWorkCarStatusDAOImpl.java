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
import com.nixsolutions.dao.OrderInWorkCarStatusDAO;
import com.nixsolutions.entities.OrderInWorkCarStatus;

public class OrderInWorkCarStatusDAOImpl implements OrderInWorkCarStatusDAO<OrderInWorkCarStatus> {

	private final static Logger LOG = LogManager.getLogger(OrderInWorkCarStatus.class);
	private Connection conn;
	private PreparedStatement ps;
	private List<OrderInWorkCarStatus> listResult;

	public OrderInWorkCarStatusDAOImpl() {
		
	}

	@Deprecated
	@Override
	public boolean create(OrderInWorkCarStatus t) {
		return false;
	}

	@Deprecated
	@Override
	public boolean update(OrderInWorkCarStatus t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean delete(OrderInWorkCarStatus t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrderInWorkCarStatus findByPK(int id) {
		boolean resultExecution;
		OrderInWorkCarStatus orderInWork = new OrderInWorkCarStatus();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"SELECT * FROM order_in_work oiw join order_status os on oiw.order_status_id=os.order_status_id join car c on oiw.car_id=c.car_id WHERE order_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					orderInWork.setId(rs.getInt("order_id"));
					orderInWork.setOrder_status_id(rs.getInt("order_status_id"));
					orderInWork.setOrder_status_name(rs.getString("order_status_name"));
					orderInWork.setVin(rs.getString("vin"));
					orderInWork.setModel(rs.getString("model"));
					orderInWork.setDescription(rs.getString("description"));
					orderInWork.setDatetime_start(rs.getTimestamp("datetime_start"));
					orderInWork.setDatetime_end(rs.getTimestamp("datetime_end"));
				}
			} else {
				orderInWork = null;
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
		return orderInWork;
	}

	@Override
	public List<OrderInWorkCarStatus> getAll() {
		boolean resultExecution;
		listResult = new ArrayList<>();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"SELECT * FROM order_in_work oiw join order_status os on oiw.order_status_id=os.order_status_id join car c on oiw.car_id=c.car_id");
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					OrderInWorkCarStatus orderInWork = new OrderInWorkCarStatus();
					orderInWork.setId(rs.getInt("order_id"));
					orderInWork.setOrder_status_id(rs.getInt("order_status_id"));
					orderInWork.setOrder_status_name(rs.getString("order_status_name"));
					orderInWork.setVin(rs.getString("vin"));
					orderInWork.setModel(rs.getString("model"));
					orderInWork.setDescription(rs.getString("description"));
					orderInWork.setDatetime_start(rs.getTimestamp("datetime_start"));
					orderInWork.setDatetime_end(rs.getTimestamp("datetime_end"));
					listResult.add(orderInWork);
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
		return listResult;
	}

}
