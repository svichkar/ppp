package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CellDao;
import com.nixsolutions.entity.Cell;
import com.nixsolutions.service.CellService;

@Service
public class CellServiceImpl implements CellService{

	@Autowired
	private CellDao cellDao;
	
	@Override
	public List<Cell> getAllCells() {
		return cellDao.getAllCells();
	}

	@Override
	public Cell getCellById(Long celltId) {
		return cellDao.getCellById(celltId);
	}

	@Override
	public Cell getCellByName(String celltName) {
		return cellDao.getCellByName(celltName);
	}

	@Override
	public void createCell(Cell cell) {
		cellDao.createCell(cell);
	}

	@Override
	public void updateCell(Cell cell) {
		cellDao.updateCell(cell);
	}

	@Override
	public void deleteCell(Cell cell) {
		cellDao.deleteCell(cell);
	}

}
