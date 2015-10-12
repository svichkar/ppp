package com.nixsolutions;

public class StartApp {

    @ToString
    public int exp = 123;

    @ToString
    public String su = "BLA BLA";

    @ToString
    public Long longg = 58974L;

    public static void main(String[] args) {

	// Task 1
	Annotator a = new Annotator(StartApp.class);

	// Task 2
	MyClassLoader cl = new MyClassLoader();
	cl.setPath("D:/Java/JavaDepartment/Project/logovskoy/javappp/02_OOP/bin/src/com/nixsolutions/Figure.class");
	Class c = cl.getInstanceByPath();
	cl.setPath("D:/Java/JavaDepartment/Project/logovskoy/javappp/02_OOP/bin/src/com/nixsolutions/Circle.class");
	Class cd = cl.getInstanceByPath();

	System.out.println(c);
	System.out.println(cd);

    }

}
