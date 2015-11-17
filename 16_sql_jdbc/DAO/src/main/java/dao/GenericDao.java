package dao;

import java.sql.SQLException;

public interface GenericDao<T> {

	boolean create(T t) throws SQLException;

	boolean update(T t) throws SQLException;

	boolean delete(T t) throws SQLException;

	T findByPK(int id) throws SQLException;

	T findByName(String name) throws SQLException;
}
