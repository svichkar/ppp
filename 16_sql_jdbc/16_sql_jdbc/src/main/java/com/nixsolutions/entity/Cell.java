package com.nixsolutions.entity;

public class Cell {
	private int cellId;
	private String name;

	public Cell(int cellId, String name) {
		super();
		this.cellId = cellId;
		this.name = name;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
