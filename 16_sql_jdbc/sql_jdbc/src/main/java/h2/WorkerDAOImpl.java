package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.PersistenceException;
import entities.Worker;

public class WorkerDAOImpl extends AbstractH2DAO<Worker>{
	
	public WorkerDAOImpl(Connection conn) {
		super(conn);
	}

	private class ChWorker extends Worker {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	@Override
	public Worker create() throws PersistenceException {
		Worker worker = new Worker();
		return createFrom(worker);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM sqllab.worker WHERE worker_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM sqllab.worker;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE sqllab.worker SET %1$s WHERE worker_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM sqllab.worker WHERE worker_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO sqllab.worker (first_name, last_name, specialization_id, status_id) VALUES (%1$s);";
	}

	@Override
	public List<Worker> parseResults(ResultSet rs) throws PersistenceException {
		List<Worker> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChWorker worker = new ChWorker();
				worker.setId(rs.getInt("worker_id"));
				worker.setFirstName(rs.getString("first_name"));
				worker.setLastName(rs.getString("last_name"));
				worker.setSpecializationId(rs.getInt("specialization_id"));
				worker.setStatusId(rs.getInt("status_id"));
				resultList.add(worker);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}
}
