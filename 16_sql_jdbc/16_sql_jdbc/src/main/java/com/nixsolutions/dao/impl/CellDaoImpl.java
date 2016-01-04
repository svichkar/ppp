package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.app.H2ConnManager;
import com.nixsolutions.dao.CellDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Cell;

public class CellDaoImpl implements CellDao{
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Cell> getAllCells() {
		LOG.entry();
		String sql = "SELECT * FROM cell;";
		List<Cell> cells = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Cell cell = new Cell(result.getString("name"));
				cell.setCellId(result.getInt("cell_id"));
				cells.add(cell);
			}
			LOG.trace("all the cells were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all cells", e));
		}
		return LOG.exit(cells);
	}

	@Override
	public Cell getCellById(int cellId) {
		LOG.entry(cellId);
		String sql = "SELECT * FROM cell WHERE cell_id = ?;";
		Cell cell = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, cellId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				cell = new Cell(result.getString("name"));
				cell.setCellId(result.getInt("cell_id"));
			}
			LOG.trace("the cell was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a cell by Id", e));
		}
		return LOG.exit(cell);
	}

	@Override
	public void createCell(Cell cell) {
		LOG.entry(cell);
		String sql = "INSERT INTO cell (name) VALUES (?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, cell.getName());
			statem.executeUpdate();
			LOG.exit("cell was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create a cell", e));
		}
	}

	@Override
	public void updateCell(Cell cell) {
		LOG.entry(cell);
		String sql = "UPDATE cell SET name = ? WHERE cell_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, cell.getName());
			statem.setLong(2, cell.getCellId());
			statem.executeUpdate();
			LOG.exit("cell with id: " + cell.getCellId() + " was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the cell", e));
		}
	}

	@Override
	public void deleteCell(Cell cell) {
		LOG.entry(cell);
		String sql = "DELETE FROM cell WHERE cell_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, cell.getCellId());
			statem.executeUpdate();
			LOG.exit("cell with id: " + cell.getCellId() + " was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the cell", e));
		}
	}

}
