package com.nixsolutions;

/**
 * Created by konstantin on 11/30/2015.
 */
public class TestClass {

    private int number;
    private String word;
    @Public
    private double course;

    public double getCourse() {
        return course;
    }

    public int getNumber() {
        return number;
    }

    public String getWord() {
        return word;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
