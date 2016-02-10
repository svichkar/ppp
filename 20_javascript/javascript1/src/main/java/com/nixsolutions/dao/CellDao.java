package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Cell;

public interface CellDao {
	 List<Cell> getAllCells();
	 Cell getCellById(Long celltId);
	 Cell getCellByName(String celltName);
	 void createCell(Cell cell);
	 void updateCell(Cell cell);
	 void deleteCell(Cell cell);
}

