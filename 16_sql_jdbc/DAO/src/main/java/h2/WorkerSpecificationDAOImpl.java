package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GenericDao;
import entities.WorkerSpecification;

public class WorkerSpecificationDAOImpl implements
		GenericDao<WorkerSpecification> {

	Connection conn;

	private class ChWorkerSpecification extends WorkerSpecification {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	public WorkerSpecificationDAOImpl(Connection conn) throws Exception {
		this.conn = conn;
		//define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	@Override
	public boolean create(WorkerSpecification t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO worker_specification (spec_name) VALUES (?)");
		ps.setString(1, t.getSpec_name());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(WorkerSpecification t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE worker_specification SET spec_name=? WHERE spec_id=?");
		ps.setString(1, t.getSpec_name());
		ps.setInt(2, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(WorkerSpecification t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM worker_specification WHERE spec_id=?");

		ps.setInt(1, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public WorkerSpecification findByPK(int id) throws SQLException {
		int resultExecution;
		ChWorkerSpecification part = new ChWorkerSpecification();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM worker_specification WHERE spec_id=?");
		ps.setInt(1, id);
		resultExecution = ps.executeUpdate();
		if (resultExecution > 0) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				part.setId(rs.getInt("spec_id"));
				part.setSpec_name(rs.getString("spec_name"));
			}
		} else {
			return null;
		}
		return part;
	}

	@Override
	public WorkerSpecification findByName(String name) throws SQLException {
		boolean resultExecution;
		ChWorkerSpecification part = new ChWorkerSpecification();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM worker_specification WHERE spec_name=?");
		ps.setString(1, name);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				part.setId(rs.getInt("spec_id"));
				part.setSpec_name(rs.getString("spec_name"));
			}
		} else {
			return null;
		}
		return part;
	}

}
