package com.nixsolutions.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.nixsolutions.dao.GenericDAO;
import com.nixsolutions.entities.AbstractEntity;
import com.nixsolutions.entities.PersistenceException;

public abstract class AbstractH2DAO<T extends AbstractEntity> implements GenericDAO<T> {
	
	private Connection conn;
	
	public abstract String getSelectByID();
	
	public abstract String getSelectAll();
	
	public abstract String getUpdate();
	
	public abstract String getDelete();
	
	public abstract String getCreate();
	
	public abstract List<T> parseResults(ResultSet rs) throws PersistenceException;
	
	@Override
	public T getByPK(int id) throws PersistenceException {
		List<T> resultList = null;
		String sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new PersistenceException("More than one result by presumably unique id. ID: " + id);
		}
		return resultList.get(0);
	}
	
	@Override
	public List<T> getAll() throws PersistenceException{
		List<T> resultList = null;
		String sql = getSelectAll();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}
	
	@Override
	public void update(T entity) throws PersistenceException {
		String sql = getUpdate();
/*		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, entity.toString());
			stmt.setInt(2, entity.getId());
			int updCount = stmt.executeUpdate();*/
		try {
			Statement stmt = conn.createStatement();
			int updCount = stmt.executeUpdate(String.format(sql, entity.toString(), entity.getId()));
			if (updCount != 1) {
				throw new PersistenceException("On update either multiple or no records were affected. Count: " + updCount);
			}
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
	}
	
	@Override
	public void delete(T entity) throws PersistenceException {
		String sql = getDelete();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			int updCount = stmt.executeUpdate();
			if (updCount != 1) {
				throw new PersistenceException("On deletion either multiple or no records were affected. Count: " + updCount);
			}
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
	}
	
	@Override
	public T createFrom(T entity) throws PersistenceException {
		T entInstance = null;
		String sql = getCreate();
		int pk = 0;
		//try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		//stmt.setString(1, entity.getValuesCommaSeparated());
		try {
			Statement stmt = conn.createStatement();			
			int insertCount = stmt.executeUpdate(String.format(sql, entity.getValuesCommaSeparated()), 1);
			if (insertCount != 1) {
				throw new PersistenceException("On creation either multiple or no records were affected. Count: " + insertCount);
			}
			ResultSet keySet = stmt.getGeneratedKeys();
			while (keySet.next()) {
				pk = keySet.getInt(1);
			}
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, pk);
			ResultSet rs = stmt.executeQuery();
			List<T> resultList = parseResults(rs);
			if (resultList == null || resultList.size() != 1) {
				throw new PersistenceException("No records found by id. ID: " + pk);
			}
			entInstance = resultList.get(0);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		return entInstance;
	}
	
	public AbstractH2DAO(Connection conn) {
		this.conn = conn;
	}
}
