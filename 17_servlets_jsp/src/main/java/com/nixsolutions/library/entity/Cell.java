package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class Cell {
    private Integer cellId;
    private String name;

    public Cell(Integer cellId, String name) {
        this.cellId = cellId;
        this.name = name;
    }

    public Cell(String name) {
        this.name = name;
    }

    public Integer getCellId() {
        return cellId;
    }

    public String getName() {
        return name;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
