package com.fieldvalues;

/**
 * Created by pantiukhin on 2/17/2016.
 */
public class ClassWithFields {
    Long longField;
    @Public
    public String stringField;
    private Integer intField;
    @Public
    protected Double doubleField;

    public Long getLongField() {
        return longField;
    }

    public Double getDoubleField() {
        return doubleField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public void setIntField(Integer intField) {
        this.intField = intField;
    }

    public void setLongField(Long longField) {
        this.longField = longField;
    }

    public String getStringField() {
        return stringField;
    }

    public Integer getIntField() {
        return intField;
    }

    public void setDoubleField(Double doubleField) {
        this.doubleField = doubleField;
    }


}
