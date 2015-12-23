package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class Category {
    private Integer categoryId;
    private String name;

    public Category(Integer categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
