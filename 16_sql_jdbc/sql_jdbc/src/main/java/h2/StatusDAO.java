package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractDAO;
import entities.PersistenceException;
import entities.Status;

public class StatusDAO extends AbstractDAO<Status> {

	private class ChStatus extends Status {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}
	
	public StatusDAO(Connection conn) {
		super(conn);
	}

	@Override
	public Status create() throws PersistenceException {
		Status status = new Status();
		return createFrom(status);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM status WHERE status_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM status;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE status SET %1$s WHERE status_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM status WHERE status_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO status (status_name) VALUES (%1$s);";
	}

	@Override
	public List<Status> parseResults(ResultSet rs) throws PersistenceException {
		List<Status> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChStatus status = new ChStatus();
				status.setId(rs.getInt("status_id"));
				status.setStatusName(rs.getString("status_name"));
				resultList.add(status);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

}
