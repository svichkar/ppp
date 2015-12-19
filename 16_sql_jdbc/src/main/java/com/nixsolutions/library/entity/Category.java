package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class Category {
    private int categoryId;
    private String name;

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }
}
