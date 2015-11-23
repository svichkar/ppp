package com.nixsolutions.dao.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.util.ConnectionManager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDaoImpl {
    private final static Logger LOG = LogManager.getLogger(AbstractDaoImpl.class.getName());

    protected <T> void insert(String[] fields, String[] values, String tableName) {
	try (Connection conn = ConnectionManager.getConnection()) {
	    try (Statement stmt = conn.createStatement()) {
		stmt.execute("INSERT INTO " + tableName + "(" + String.join(", ", fields) + ") VALUES ('" + String.join("', '", values) + "')");
	    }
	} catch (SQLException e) {
	    LOG.error(e);
	}
    }

    protected void delete(String idField, int id, String tableName) {
	try (Connection conn = ConnectionManager.getConnection()) {
	    try (Statement stmt = conn.createStatement()) {
		stmt.execute("DELETE FROM " + tableName + " WHERE " + idField + " = '" + id + "'");
	    }
	} catch (SQLException e) {
	    LOG.error(e);
	}
    }

    protected <T> List find(String field, String value, Map<String, String> mapColName, Class<T> clazz) {
	try (Connection conn = ConnectionManager.getConnection()) {
	    try (Statement stmt = conn.createStatement()) {
		ResultSet result = stmt.executeQuery("SELECT * FROM " + clazz.getSimpleName()
			+ (field != null && value != null ? (" WHERE " + field + " = '" + value + "'") : ""));
		List<T> resultList = new ArrayList<T>();
		while (result.next()) {
		    T rowres = (T) Class.forName(clazz.getName()).newInstance();
		    for (Entry<String, String> e : mapColName.entrySet()) {
			Field f;
			if ((e.getKey().split("_"))[0].equals("PK")) {
			    f = rowres.getClass().getDeclaredField(e.getKey().split("_")[1]);
			} else {
			    f = rowres.getClass().getDeclaredField(e.getKey());
			}
			f.setAccessible(true);
			if (f.getType().getName().equals("int")) {
			    f.set(rowres, result.getInt(e.getValue()));
			} else {
			    f.set(rowres, result.getObject(e.getValue()));
			}
		    }
		    resultList.add(rowres);
		}
		return resultList;
	    }
	} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException
		| NoSuchFieldException | SecurityException e) {
	    LOG.error(e);
	    return null;
	}
    }

    protected <T> List findBySeveralFields(String[] fields, String[] values, Map<String, String> mapColName,
	    Class<T> clazz) {
	try (Connection conn = ConnectionManager.getConnection()) {
	    try (Statement stmt = conn.createStatement()) {
		List<String> searchValuesAndFields = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
		    searchValuesAndFields.add(fields[i] + " = '" + values[i] + "'");
		}
		ResultSet result = stmt.executeQuery("SELECT * FROM " + clazz.getSimpleName()
			+ (fields != null && values != null && searchValuesAndFields.size() > 0
				? (" WHERE " + String.join(" AND ", searchValuesAndFields)) : ""));
		List<T> resultList = new ArrayList<T>();
		while (result.next()) {
		    T rowres = (T) Class.forName(clazz.getName()).newInstance();
		    for (Entry<String, String> e : mapColName.entrySet()) {
			Field f;
			if ((e.getKey().split("_"))[0].equals("PK")) {
			    f = rowres.getClass().getDeclaredField(e.getKey().split("_")[1]);
			} else {
			    f = rowres.getClass().getDeclaredField(e.getKey());
			}
			f.setAccessible(true);
			if (f.getType().getName().equals("int")) {
			    f.set(rowres, result.getInt(e.getValue()));
			} else {
			    f.set(rowres, result.getObject(e.getValue()));
			}
		    }
		    resultList.add(rowres);
		}
		return resultList;
	    }
	} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException
		| NoSuchFieldException | SecurityException e) {
	    LOG.error(e);
	    return null;
	}
    }

    protected <T> void update(T obj, Map<String, String> colNamesMap, String tableName) {
	try (Connection conn = ConnectionManager.getConnection()) {
	    try (Statement stmt = conn.createStatement()) {
		List<String> setValuesToFields = new ArrayList<String>();
		String whereCondition = "";
		for (Entry<String, String> e : colNamesMap.entrySet()) {
		    if (!(e.getKey().split("_"))[0].equals("PK")) {
			Field f = obj.getClass().getDeclaredField(e.getKey());
			f.setAccessible(true);
			setValuesToFields.add(e.getValue() + " = '" + f.get(obj) + "'");
		    } else {
			Field f = obj.getClass().getDeclaredField((e.getKey().split("_"))[1]);
			f.setAccessible(true);
			whereCondition = e.getValue() + " = '" + f.get(obj) + "'";
		    }
		}
		stmt.execute("UPDATE " + tableName + " SET " + String.join(", ", setValuesToFields) + " WHERE "
			+ whereCondition);
	    }
	} catch (SQLException | NoSuchFieldException | SecurityException | IllegalArgumentException
		| IllegalAccessException e) {
	    LOG.error(e);
	}
    }
}
