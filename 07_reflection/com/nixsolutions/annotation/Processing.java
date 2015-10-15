package com.nixsolutions.annotation;


public class Processing {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		VictimClass parseIt = new VictimClass(18, "Alex", 90.0, 21);
		ObjConvertor parser = new ObjConvertor(parseIt, ToString.class);
		System.out.printf("Name of parsed object %s%n", parser.getNameOfObject());
		System.out.printf("It has structure as %s", parser.getObjectAsString());
	}

}
