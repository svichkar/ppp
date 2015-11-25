package com.nixsolutions;

import java.io.*;
import java.util.Date;

/**
 * Created by konstantin on 11/21/2015.
 */
public class TestClass implements Serializable {

    private String type;
    private transient Integer index;
    private Boolean isGood;
    private Date currentDate;

    protected TestClass() {
        setGood(true);
        setType("common");
        setIndex(1);
        setCurrentDate(new Date());
    }

    public Boolean getGood() {
        return isGood;
    }

    public void setGood(Boolean good) {
        this.isGood = good;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCurrentDate() {
        return this.currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
