package com.nixsolutions.reflect;

public class StudentEntity {

    @ToString
    private String name;
    @ToString
    private int age;
    private String group;
    private double averagePoint;

    public StudentEntity(String name, int age, String group, double averagePoint) {
        this.name = name;
        this.age = age;
        this.group = group;
        this.averagePoint = averagePoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(double averagePoint) {
        this.averagePoint = averagePoint;
    }

}
