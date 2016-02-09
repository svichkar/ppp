package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Cell;

public interface CellDao {
	public List<Cell> getAllCells();
	public Cell getCellById(Long celltId);
	public Cell getCellByName(String celltName);
	public void createCell(Cell cell);
	public void updateCell(Cell cell);
	public void deleteCell(Cell cell);
}

