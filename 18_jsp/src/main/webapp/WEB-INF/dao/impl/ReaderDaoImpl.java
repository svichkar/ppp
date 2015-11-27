package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import dao.ReaderDao;
import entity.Reader;
import util.ConnectionManager;

public class ReaderDaoImpl extends AbstractDaoImpl implements ReaderDao {

	

	@Override
	public List<Reader> getAllReaders() {
	
		return super.find(null, null);
	}

	@Override
	public boolean addReader(Reader reader) {
		// TODO Auto-generated method stub
		return super.addNewRow(Arrays.asList("name","adress"), Arrays.asList(reader.getName(),reader.getAdress()));
	}

	@Override
	public boolean updateReader(Reader reader) {
		// TODO Auto-generated method stub
		return false;
	}
	public int getCountOfDebtBooksForParticularUser(String id) {

		try (Connection conn = ConnectionManager.getConnection()) {
			try (Statement st = conn.createStatement()) {
				ResultSet rs = st.executeQuery(
						"SELECT COUNT(*) FROM journal WHERE reader_id=" + id);
				while (rs.next()) {
					return rs.getInt("COUNT(*)");
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return -1;

	}

}
