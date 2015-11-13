package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.PersistenceException;
import entities.Role;

public class RoleDAOImpl extends AbstractH2DAO<Role> {

	private class ChRole extends Role {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}
	
	public RoleDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public Role create() throws PersistenceException {
		Role role = new Role();
		return createFrom(role);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM sqllab.role WHERE role_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM sqllab.role;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE sqllab.role SET %1$s WHERE role_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM sqllab.role WHERE role_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO sqllab.role (role_name) VALUES (%1$s);";
	}

	@Override
	public List<Role> parseResults(ResultSet rs) throws PersistenceException {
		List<Role> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChRole role = new ChRole();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				resultList.add(role);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

}
