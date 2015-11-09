package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Part;
import entities.PersistenceException;

public class PartDAOImpl extends AbstractH2DAO<Part> {

	private class ChPart extends Part {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}
	
	public PartDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public Part create() throws PersistenceException {
		Part part = new Part();
		return createFrom(part);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM sqllab.part WHERE part_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM sqllab.part;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE sqllab.part SET %1$s WHERE part_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM sqllab.part WHERE part_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO sqllab.part (part_name, vendor, amount) VALUES (%1$s);";
	}

	@Override
	public List<Part> parseResults(ResultSet rs) throws PersistenceException {
		List<Part> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChPart part = new ChPart();
				part.setId(rs.getInt("part_id"));
				part.setPartName(rs.getString("part_name"));
				part.setVendor(rs.getString("vendor"));
				part.setAmount(rs.getLong("amount"));
				resultList.add(part);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

}
