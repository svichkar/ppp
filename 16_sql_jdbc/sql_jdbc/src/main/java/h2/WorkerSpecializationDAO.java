package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractDAO;
import entities.PersistenceException;
import entities.WorkerSpecialization;

public class WorkerSpecializationDAO extends AbstractDAO<WorkerSpecialization> {
	
	private class ChWorkerSpecialization extends WorkerSpecialization {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}
	
	public WorkerSpecializationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public WorkerSpecialization create() throws PersistenceException {
		WorkerSpecialization ws = new WorkerSpecialization();
		return createFrom(ws);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM worker_specialization WHERE specialization_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM worker_specialization;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE worker_specialization SET %1$s WHERE specialization_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM worker_specialization WHERE specialization_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO worker_specialization (specialization_name) VALUES (%1$s);";
	}

	@Override
	public List<WorkerSpecialization> parseResults(ResultSet rs) throws PersistenceException {
		List<WorkerSpecialization> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChWorkerSpecialization ws = new ChWorkerSpecialization();
				ws.setId(rs.getInt("specialization_id"));
				ws.setSpecName(rs.getString("specialization_name"));
				resultList.add(ws);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

}
