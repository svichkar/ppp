package com.nixsolutions.dao;

public class DAOFactory {
    public static Term getTerm() {
	return new Term();
    }

    public static Status getStatus() {
	return new Status();
    }

    public static Journal getJournal() {
	return new Journal();
    }

    public static Student getStudent() {
	return new Student();
    }

    public static Subject getSubject() {
	return new Subject();
    }

    public static StudentGroup getStudentGroup() {
	return new StudentGroup();
    }
}
